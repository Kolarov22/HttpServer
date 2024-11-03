package Proxy;

import App.HttpRequest;

public interface RequestHandler {
    String handle(HttpRequest request);
}
