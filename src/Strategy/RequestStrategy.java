package Strategy;

import App.HttpRequest;

public interface RequestStrategy {
    public void handleRequest(HttpRequest request);
}
