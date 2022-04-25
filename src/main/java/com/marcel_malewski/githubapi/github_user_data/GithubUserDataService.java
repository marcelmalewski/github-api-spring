package com.marcel_malewski.githubApi.github_user_data;

import com.marcel_malewski.githubApi.GithubServiceLayer;
import com.marcel_malewski.githubApi.repository_data.RepositoryData;
import com.marcel_malewski.githubApi.repository_data.RepositoryDataService;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class GithubUserDataService {
    private final RepositoryDataService repositoryDataService;
    private final GithubServiceLayer githubServiceLayer;
    public GithubUserDataService(RepositoryDataService repositoryDataService, GithubServiceLayer githubServiceLayer) {
        this.repositoryDataService = repositoryDataService;
        this.githubServiceLayer = githubServiceLayer;
    }

    private Map<String, Integer> aggregateLanguagesByBytes(List<RepositoryData> repositoriesData) {
        //hashmap to store aggregated languages by bytes
        Map<String, Integer> allLanguages = new HashMap<>();

        repositoriesData.forEach(repositoryData -> {
            //for every repositoryData create map from languages
            Map<String, Integer> repositoryLanguages = repositoryData.getLanguages();

            for (Map.Entry<String, Integer> entry : repositoryLanguages.entrySet()) {
                //if languages is already in allLanguages we sum bytes
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
        //Now with our link and githubUserName get response with user data
        HttpResponse<String> response = this.githubServiceLayer.getResponseFromLink(githubUserUrl);
        //get repositories of user
        List<RepositoryData> repositoriesData = this.repositoryDataService.getRepositoriesDataOfGithubUser(githubUserName);
        //aggregate languages in repositories by bytes
        Map<String, Integer> allLanguages = aggregateLanguagesByBytes(repositoriesData);

        GithubUserData githubUserData = this.githubServiceLayer.mapper.readValue(response.body(), GithubUserData.class);
        //refill languages with aggregated languages by bytes
        githubUserData.setLanguages(allLanguages);

        return githubUserData;
    }
}
