package nl.hu.eindopdracht1.domain.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.http.HttpHeaders;

import javax.transaction.Transactional;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {
    private HttpClient client = HttpClient.newHttpClient();

    public HttpResponse<String> getUserById(String uri, String userId) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .uri(URI.create(uri + "/" + userId))
                .build();
        return client.send(request, HttpResponse.BodyHandlers.ofString());
    }
}
