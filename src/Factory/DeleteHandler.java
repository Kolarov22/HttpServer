package Factory;

import App.HttpRequest;

import java.io.File;

public class DeleteHandler implements HttpMethodHandler {
    @Override
    public String handleRequest(HttpRequest request){
        String resourcePath = "C:\\Users\\Andrei\\IdeaProjects\\SDT_WebServer\\HttpServer\\src\\resources" + request.getResourceUrl().replace("/", "\\");
        File[] files = new File(resourcePath).listFiles();
        if (files == null || files.length == 0) {
            return "HTTP/1.1 404 Not Found\r\n\r\nResource not found on the server";
        }
        File resourceFile = files[0];

        if (resourceFile.exists() && resourceFile.isFile()) {
            if (resourceFile.delete()) {
                return "HTTP/1.1 200 OK\r\n\r\nResource deleted from the server";
            } else {
                return "HTTP/1.1 500 Internal Server Error\r\n\r\nFailed to delete the resource";
            }
        } else {
            return "HTTP/1.1 404 Not Found\r\n\r\nResource not found on the server";
        }

    }
}
