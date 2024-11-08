package Strategy;

import App.HttpRequest;

public class JsonStrategy implements RequestStrategy {
    @Override
    public void handleRequest(HttpRequest request) {
        if(request.getMethod().equals("GET")) {
            return;
        }

        String body = request.getBody();
        if (isValidJson(body)) {
            request.setBody(body);
            request.setContentType("application/json");
        } else {
            throw new RuntimeException("Body is not a valid JSON string");
        }


    }

    private boolean isValidJson(String body) {
        return body.trim().startsWith("{") && body.trim().endsWith("}") ||  body.trim().startsWith("[") && body.trim().endsWith("]");
    }
}