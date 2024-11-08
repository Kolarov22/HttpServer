package Factory;

import App.HttpRequest;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;

public class GetHandler implements HttpMethodHandler {
    @Override
    public String handleRequest(HttpRequest request){
        String resourcePath = "C:\\Users\\Andrei\\IdeaProjects\\SDT_WebServer\\HttpServer\\src\\resources" + request.getResourceUrl().replace("/", "\\");
        File resourceDirectory = new File(resourcePath);
        System.out.println(request);

        if (resourceDirectory.exists() && resourceDirectory.isDirectory()) {
            File[] files = resourceDirectory.listFiles();
            if (files != null) {
                files = Arrays.stream(files).filter(file -> !file.isDirectory()).toList().toArray(new File[0]);
                File resourceFile = files[0];
                String content;
            try {
                content = new String(Files.readAllBytes(resourceFile.toPath()));
                System.out.println(content);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return "HTTP/1.1 200 OK\r\nContent-Type: " + request.getContentType() + "\r\nContent-Length: " + content.length() + "\r\n\r\n" + content + "\r\n";
        } else {
                return "HTTP/1.1 404 Not Found\r\nContent-Type: text/plain\r\n\r\nResource not found";
            }

        }
        return "HTTP/1.1 404 Not Found\r\nContent-Type: text/plain\r\n\r\nResource not found";
    }
}
