package com.marcel_malewski.githubapi;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class GithubServiceLayer {
    private final HttpClient client = HttpClient.newHttpClient();
    protected final ObjectMapper mapper = new ObjectMapper()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    protected HttpResponse<String> getResponseFromLink(String url) throws URISyntaxException, IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .headers("Accept", "application/vnd.github.v3+json", "Authorization", "Bearer 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")
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
        else if(response.statusCode() == 403) {
            throw new ResponseStatusException(
                    HttpStatus.TOO_MANY_REQUESTS,
                    "API Rate limit exceeded"
            );
        }
        return response;
    }
}
