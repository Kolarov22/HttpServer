package Strategy;

import App.HttpRequest;

public class RequestContext {
    private RequestStrategy strategy;

    public void setStrategy(RequestStrategy strategy){
        this.strategy = strategy;
    }

    public void executeStrategy(HttpRequest request){
        strategy.handleRequest(request);
    }
}
