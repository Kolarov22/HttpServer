package Factory;

import App.HttpRequest;

public class DeleteHandler implements HttpMethodHandler {
    @Override
    public String handleRequest(HttpRequest request){
        return "HTTP/1.1 200 OK\r\nContent-Type: "+ request.getContentType() + "\r\n\r\nBody: " + request.getBody() + "\r\nContent-Length: " + request.getBody().length();

    }
}
