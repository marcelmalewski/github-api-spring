package com.marcel_malewski.githubapi.repository_data;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class RepositoryDataService {
    public List<RepositoryDataWithLanguages> getRepositoriesDataOfGithubUser(String githubUserName) throws URISyntaxException, IOException, InterruptedException {
        String repoUrl = String.format("https://api.github.com/users/%s/repos", githubUserName);
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .header("Accept", "application/vnd.github.v3+json")
                .uri(new URI(repoUrl))
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        ObjectMapper mapper = new ObjectMapper()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        List<RepositoryData> repositoriesData = mapper.readValue(response.body(), new TypeReference<>() {});

        ArrayList<RepositoryDataWithLanguages> repositoriesDataWithLanguages = new ArrayList<>();

        repositoriesData.forEach(repositoryData -> {
            String languages_url = repositoryData.getLanguages_url();

            try {
                HttpRequest request2 = HttpRequest.newBuilder()
                        .GET()
                        .header("Accept", "application/vnd.github.v3+json")
                        .uri(new URI(languages_url))
                        .build();

                HttpResponse<String> response2 = client.send(request2, HttpResponse.BodyHandlers.ofString());

                repositoriesDataWithLanguages.add(new RepositoryDataWithLanguages(repositoryData.getName(), response2.body()));
            } catch (IOException | InterruptedException | URISyntaxException e) {
                throw new RuntimeException(e);
            }
        });

        return repositoriesDataWithLanguages;
    }
}
