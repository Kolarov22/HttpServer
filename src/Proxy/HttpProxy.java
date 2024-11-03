package Proxy;

import App.HttpRequest;

import java.util.HashMap;
import java.util.Map;

public class HttpProxy implements RequestHandler {
    private final RequestHandler realServer;
    private final Map<String, String> cache;

    public HttpProxy(RequestHandler realServer) {
        this.realServer = realServer;
        this.cache = new HashMap<>();
    }

    @Override
    public String handle(HttpRequest request) {
        String cacheKey = request.getMethod() + request.getResourceUrl();
        if (cache.containsKey(cacheKey)) {
            System.out.println("Returning cached response");
            return cache.get(cacheKey);
        } else {
            String response = realServer.handle(request);
            cache.put(cacheKey, response);
            return response;
        }
    }
}