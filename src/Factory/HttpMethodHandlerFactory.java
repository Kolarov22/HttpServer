package Factory;

public class HttpMethodHandlerFactory {
    public static HttpMethodHandler getHandler(String methodName){
        return switch (methodName) {
            case "GET" -> new GetHandler();
            case "POST" -> new PostHandler();
            case "DELETE" -> new DeleteHandler();
            default -> null;
        };
    }
}
