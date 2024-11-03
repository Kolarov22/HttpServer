package Factory;

import App.HttpRequest;

public class PostHandler implements HttpMethodHandler {
    @Override
    public String handleRequest(HttpRequest request){
        return "HTTP/1.1 201 Created\r\nContent-Type: "+ request.getContentType() + "\r\nLocation: " + request.getResourceUrl() + "\r\n\r\n" + request.getBody();

    }
}
