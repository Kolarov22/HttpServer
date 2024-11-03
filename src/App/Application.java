package App;

import ObjectPool.Server;

public class Application {
    public static void main(String[] args) {
        Server server = new Server(5);
        int port = 8080;
        server.start(port);
    }
}