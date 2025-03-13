package com.eulerity.hackathon.imagefinder;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashSet;
import java.util.concurrent.*;

import com.eulerity.hackathon.imagefinder.Exceptions.InvalidUrlException;
import com.eulerity.hackathon.imagefinder.Exceptions.UrlConnectionError;
import com.eulerity.hackathon.imagefinder.Services.WebCrawler;
import com.eulerity.hackathon.imagefinder.Utils.Utils;
import org.junit.Assert;
import org.junit.Test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.mockito.Mockito;

import static org.junit.Assert.*;

/**
 * The type Image finder test.
 */
public class ImageFinderTest {


    /**
     * The Request.
     */
    public HttpServletRequest request;
    /**
     * The Response.
     */
    public HttpServletResponse response;
    /**
     * The Sw.
     */
    public StringWriter sw;
    /**
     * The Session.
     */
    public HttpSession session;

    /**
     * Sets up.
     *
     * @throws Exception the exception
     */
    @Before
    public void setUp() throws Exception {

        request = Mockito.mock(HttpServletRequest.class);
        response = Mockito.mock(HttpServletResponse.class);

        sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);

        Mockito.when(response.getWriter()).thenReturn(pw);
        Mockito.when(request.getRequestURI()).thenReturn("/foo/foo/foo");
        Mockito.when(request.getRequestURL()).thenReturn(new StringBuffer("http://localhost:8080/foo/foo/foo"));
        Mockito.when(request.getParameter("url")).thenReturn("https://thecoderenroute.github.io/");
        session = Mockito.mock(HttpSession.class);
        Mockito.when(request.getSession()).thenReturn(session);
    }

    /**
     * Test valid url domain.
     *
     * @throws InvalidUrlException the invalid url exception
     */
    @Test
    public void testValidUrlDomain() throws InvalidUrlException {
        String url = "https://www.google.com/foo/foo/foo?q=p";

        String domain = Utils.getDomain(url);
        Assert.assertEquals("google", domain);

    }

    /**
     * Test invalid url domain.
     */
    @Test
    public void testInvalidUrlDomain() {
        String url = "https://google/foo/foo/foo?q=p";

        assertThrows(
                "Invalid URL",
                InvalidUrlException.class,
                () -> Utils.getDomain(url)
        );

    }

    /**
     * Test Invalid url connection.
     */
    @Test
    public void testInvalidUrlConnection() {

        String url = "https://abcdefhi.buh/";

        ExecutorService executorService = Executors.newSingleThreadExecutor();

        UrlConnectionError uce = assertThrows(UrlConnectionError.class,
                () -> {
                    try {
                        WebCrawler crawler = new WebCrawler(url, 0, executorService, new HashSet<>());
                        Future<ConcurrentHashMap<String, CopyOnWriteArrayList<String>>> future = executorService.submit(crawler);
                        future.get();
                    } catch (ExecutionException e) {
                        throw e.getCause();
                    }
                }
        );

        assertEquals("Cannot connect to " + url, uce.getMessage());

    }


}



