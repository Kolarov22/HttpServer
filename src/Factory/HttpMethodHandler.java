package Factory;


import App.HttpRequest;

public interface HttpMethodHandler {
    String handleRequest(HttpRequest request);
}
