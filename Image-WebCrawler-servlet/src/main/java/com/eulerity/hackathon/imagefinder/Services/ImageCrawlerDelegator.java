package com.eulerity.hackathon.imagefinder.Services;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.*;

/**
 * The type ImageCrawlerDelegator.
 */
public class ImageCrawlerDelegator {

    /**
     * Explore list.
     *
     * @param url the url
     * @return the list
     */
    public ConcurrentHashMap<String, CopyOnWriteArrayList<String>> explore(String url) throws ExecutionException, InterruptedException {

        ExecutorService executorService = Executors.newFixedThreadPool(6);

        Set<String> visited = Collections.synchronizedSet(new HashSet<>());

        url = url.trim();
        WebCrawler sgp;
        synchronized (visited) {
            sgp = new WebCrawler(url, 0, executorService, visited);
        }
        Future<ConcurrentHashMap<String, CopyOnWriteArrayList<String>>> ft = executorService.submit(sgp);

        ConcurrentHashMap<String, CopyOnWriteArrayList<String>> allImages = ft.get();

        System.out.println("Finished with " + url);
        return allImages;
    }

}
