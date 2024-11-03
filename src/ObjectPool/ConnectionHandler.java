package ObjectPool;

import App.HttpRequest;
import Proxy.RequestHandler;
import Strategy.JsonStrategy;
import Strategy.PlainTextStrategy;
import Strategy.RequestContext;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class ConnectionHandler implements Runnable {
    private final Socket clientSocket;
    private final RequestHandler requestHandler;

    public ConnectionHandler(Socket clientSocket, RequestHandler requestHandler) {
        this.clientSocket = clientSocket;
        this.requestHandler = requestHandler;
    }

    @Override
    public void run() {

        try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
             PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {



            String requestLine = in.readLine();
            if (requestLine == null || requestLine.isEmpty()) {
                return;
            }
            String[] requestParts = requestLine.split(" ");
            if (requestParts.length < 3) {
                return;
            }
            String method = requestParts[0];
            String resourceUrl = requestParts[1];


            Map<String, String> headers = new HashMap<>();
            String line;
            while ((line = in.readLine()) != null && !line.isEmpty()) {
                String[] header = line.split(": ", 2);
                if (header.length == 2) {
                    headers.put(header[0], header[1]);
                }
            }

            String contentType = headers.getOrDefault("Content-Type", "text/plain");
            RequestContext requestContext = new RequestContext();
            if (contentType.equals("application/json")) {
                requestContext.setStrategy(new JsonStrategy());
            } else {
                requestContext.setStrategy(new PlainTextStrategy());
            }

            StringBuilder body = new StringBuilder();
            if (headers.containsKey("Content-Length")) {
                int contentLength = Integer.parseInt(headers.get("Content-Length"));
                for (int i = 0; i < contentLength; i++) {
                    body.append((char) in.read());
                }
            }

            HttpRequest request = new HttpRequest(method, resourceUrl, contentType, body.toString());
            requestContext.executeStrategy(request);

            System.out.println(request);
            System.out.println("Sending request to the server...");

            String response = requestHandler.handle(request);

            out.print(response);
            out.flush();
            System.out.println("Response sent\n");



        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}