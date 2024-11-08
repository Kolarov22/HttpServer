package Factory;

import App.HttpRequest;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;


public class PostHandler implements HttpMethodHandler {
    @Override
    public String handleRequest(HttpRequest request){
        String resourcePath = "C:\\Users\\Andrei\\IdeaProjects\\SDT_WebServer\\HttpServer\\src\\resources" + request.getResourceUrl().replace("/", "\\");
        File resourceDirectory = new File(resourcePath);
        System.out.println(request);

        if (resourceDirectory.exists() && resourceDirectory.isDirectory()) {
            String extension = request.getContentType().equals("application/json") ? ".json" : ".txt";
            String[] fileName = request.getResourceUrl().split("/");
            File resourceFile = new File(resourcePath + "\\" + fileName[fileName.length-1] + extension);

            try {
                Files.write(resourceFile.toPath(), request.getBody().getBytes());
                return "HTTP/1.1 201 Created\r\n\r\nResource created on the server";

            } catch (IOException e) {
                return "HTTP/1.1 409 Conflict\r\n\r\nConflict with the API, check your endpoint";

            }

        } else {
                return "HTTP/1.1 409 Conflict\r\n\r\nConflict with the API, check your endpoint";
            }

    }
}
