package com.eulerity.hackathon.imagefinder;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutionException;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.eulerity.hackathon.imagefinder.Services.ImageCrawlerDelegator;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class ImageFinder extends HttpServlet {

    private static final Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    private static final long serialVersionUID = 1L;

    protected static final Gson GSON = new GsonBuilder().disableHtmlEscaping().setPrettyPrinting().create();

    @Override
    protected final void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ImageCrawlerDelegator crawlerHandler = new ImageCrawlerDelegator();

        resp.setContentType("text/json");

        String url = req.getParameter("url");

        System.out.println("Crawling the given url:" + url);

        try {
            ConcurrentHashMap<String, CopyOnWriteArrayList<String>> images = crawlerHandler.explore(url);
            resp.setHeader("og-url", url);
            resp.getWriter().print(GSON.toJson(images));
        } catch (ExecutionException | InterruptedException e) {
            resp.setStatus(500);
            Map<String, String> error = new HashMap<>();
            error.put("Error", "Faced server error " + e.getMessage());
            logger.finer(e.getMessage());
            resp.getWriter().write(GSON.toJson(error));
        }
    }


    @Override
    protected final void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        System.out.println(" here URL rcvd: ");
    }
}