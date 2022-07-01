package brysonv.minecraftconnect;


import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

public class Messenger {
    public static HttpRequest request;
    public static HttpClient client;
    public static void send(String msg){
        client = HttpClient.newHttpClient();
        request = HttpRequest.newBuilder().uri(URI.create(MinecraftConnect.config.getString("webhook")))
            .timeout(Duration.ofMinutes(1))
            .header("Content-Type", "application/json")
            .POST(HttpRequest.BodyPublishers.ofString(msg))
            .build();
        client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
            .thenApply(HttpResponse::body)
            .thenAccept(System.out::println)
            .join();
    }
}
