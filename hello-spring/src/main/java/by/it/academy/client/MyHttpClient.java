package by.it.academy.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class MyHttpClient {

    private static final Logger log = LoggerFactory.getLogger(MyHttpClient.class);

    public static void main(String[] args) throws IOException, InterruptedException {

        String body = createPostBody();
        final HttpResponse<String> response = post(body);

        log.info("Response code: {}", response.statusCode());
        log.info("Response body: {}", response.body());
    }

    private static String createPostBody() {
        return "{\"userId\":\"Igor\",\"emailAddress\":\"igor@mail.ru\",\"mobilePhone\":\"80293333333\"}";
    }

    private static HttpResponse<String> post(String body) throws IOException, InterruptedException {
        HttpClient httpClient = HttpClient.newHttpClient();

        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/hello-spring/recipients/bab25730-992e-4a3c-b58c-760468fe4814"))
                .POST(HttpRequest.BodyPublishers.ofString(body))
                .header("Content-Type", "application/json")
                .build();

        return httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
    }

}
