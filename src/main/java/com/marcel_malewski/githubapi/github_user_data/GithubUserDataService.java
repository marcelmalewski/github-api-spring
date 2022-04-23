package com.marcel_malewski.githubapi.github_user_data;

import com.marcel_malewski.githubapi.GithubService;
import com.marcel_malewski.githubapi.repository_data.RepositoryData;
import com.marcel_malewski.githubapi.repository_data.RepositoryDataService;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class GithubUserDataService extends GithubService {
    private final RepositoryDataService repositoryDataService;

    public GithubUserDataService(RepositoryDataService repositoryDataService) {
        this.repositoryDataService = repositoryDataService;
    }

    private Map<String, Integer> addAggregatedLanguagesByBytes(List<RepositoryData> repositoriesData) {
        Map<String, Integer> allLanguages = new HashMap<>();

        repositoriesData.forEach(repositoryData -> {
            Map<String, Integer> repositoryLanguages = repositoryData.getLanguages();

            for (Map.Entry<String, Integer> entry : repositoryLanguages.entrySet()) {
                if (allLanguages.containsKey(entry.getKey())) {
                    int updatedValue = entry.getValue() + allLanguages.get(entry.getKey());

                    allLanguages.put(entry.getKey(), updatedValue);
                } else {
                    allLanguages.put(entry.getKey(), entry.getValue());
                }
            }
        });

        return allLanguages;
    }

    public GithubUserData getGithubUserData(String githubUserName) throws IOException, InterruptedException, URISyntaxException {
        String githubUserUrl = String.format("https://api.github.com/users/%s", githubUserName);

        HttpResponse<String> response = getResponseFromLink(githubUserUrl);

        List<RepositoryData> repositoriesData = repositoryDataService.getRepositoriesDataOfGithubUser(githubUserName);

        Map<String, Integer> allLanguages = addAggregatedLanguagesByBytes(repositoriesData);

        GithubUserData githubUserData =  this.mapper.readValue(response.body(), GithubUserData.class);
        githubUserData.setLanguages(allLanguages);

        return githubUserData;
    }
}
