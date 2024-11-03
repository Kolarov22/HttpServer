package ObjectPool;

import App.HttpRequest;
import Factory.HttpMethodHandlerFactory;
import Proxy.HttpProxy;
import Proxy.RequestHandler;

import java.io.IOException;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server implements RequestHandler{
    private final ExecutorService executorService;
    private final HttpProxy proxy;

    public Server(int poolSize) {
        this.executorService = Executors.newFixedThreadPool(poolSize);
        this.proxy = new HttpProxy(this);

    }

    public void start(int port) {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            while (true) {
                Socket clientSocket = serverSocket.accept();
                executorService.submit(new ConnectionHandler(clientSocket, this.proxy));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String handle(HttpRequest request) {
        System.out.println("Handling request on the server...");
        return HttpMethodHandlerFactory.getHandler(request.getMethod()).handleRequest(request);
    }
}