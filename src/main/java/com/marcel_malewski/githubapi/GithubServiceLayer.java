package com.marcel_malewski.githubApi;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Component
public class GithubServiceLayer {
    private final HttpClient client = HttpClient.newHttpClient();
    public final ObjectMapper mapper = new ObjectMapper()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    public HttpResponse<String> getResponseFromLink(String url) throws URISyntaxException, IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .header("Accept", "application/vnd.github.v3+json")
                .uri(new URI(url))
                .build();
        HttpResponse<String> response = this.client.send(request, HttpResponse.BodyHandlers.ofString());
        //handle when githubUser is not found
        if(response.statusCode() == 404) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "GithubUser not found"
            );
        }
        return response;
    }
}
