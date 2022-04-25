package com.marcel_malewski.githubApi.repository_data;

import com.fasterxml.jackson.core.type.TypeReference;
import com.marcel_malewski.githubApi.GithubServiceLayer;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class RepositoryDataService {
    private final GithubServiceLayer githubServiceLayer;

    public RepositoryDataService(GithubServiceLayer githubServiceLayer) {
        this.githubServiceLayer = githubServiceLayer;
    }

    private List<RepositoryData> addLanguagesToRepositoriesData(List<RepositoryData> repositoriesData, String githubUserName) {
        repositoriesData.forEach(repositoryData -> {
            String repoLanguagesUrl = String.format("https://api.github.com/repos/%s/%s/languages", githubUserName, repositoryData.getName());

            try {
                //get languages as json
                HttpResponse<String> response = this.githubServiceLayer.getResponseFromLink(repoLanguagesUrl);
                //convert json to map of languages
                Map<String, Integer> mappedLanguages = this.githubServiceLayer.mapper.readValue(response.body(), new TypeReference<>() {
                });
                //add hashmap with languages to specific repositoryData
                repositoryData.setLanguages(mappedLanguages);
            } catch (URISyntaxException | IOException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        return repositoriesData;
    }

    private ArrayList<RepositoryData> getAllRepositoriesData(int numberOfPages, String githubUserName) throws URISyntaxException, IOException, InterruptedException {
        ArrayList<RepositoryData> allRepositoriesData = new ArrayList<>();

        //now get every page
        for (int i = 1; i <= numberOfPages; i++) {
            String repositoriesUrlWithPages = String.format("https://api.github.com/users/%s/repos?page=%o&per_page=100", githubUserName, i);
            HttpResponse<String> response = this.githubServiceLayer.getResponseFromLink(repositoriesUrlWithPages);

            List<RepositoryData> repositoriesData = this.githubServiceLayer.mapper.readValue(response.body(), new TypeReference<>() {});
            allRepositoriesData.addAll(repositoriesData);
        }

        return allRepositoriesData;
    }

    public List<RepositoryData> getRepositoriesDataOfGithubUser(String githubUserName) throws IOException, URISyntaxException, InterruptedException {
        String githubUserInfoUrl = String.format("https://api.github.com/users/%s", githubUserName);
        //get data about githubUser as json
        HttpResponse<String> responseWithGithubUserData = this.githubServiceLayer.getResponseFromLink(githubUserInfoUrl);
        //change json to object with number of public repos
        GithubUserWithNumberOfPublicRepos githubUserWithNumberOfPublicRepos = this.githubServiceLayer.mapper.readValue(responseWithGithubUserData.body(), GithubUserWithNumberOfPublicRepos.class);

        int numberOfPages = githubUserWithNumberOfPublicRepos.getPublic_repos();
        //from github api i can get max 100 repos in one request
        if (numberOfPages > 100) {
            ArrayList<RepositoryData> allRepositoriesData = getAllRepositoriesData(numberOfPages, githubUserName);

            //now refill languages
            return addLanguagesToRepositoriesData(allRepositoriesData, githubUserName);
        } else {
            //if number of repos is less than 100 we need only one request
            String repositoriesUrl = String.format("https://api.github.com/users/%s/repos?per_page=100", githubUserName);
            HttpResponse<String> response = this.githubServiceLayer.getResponseFromLink(repositoriesUrl);
            List<RepositoryData> repositoriesData = this.githubServiceLayer.mapper.readValue(response.body(), new TypeReference<>() {});

            //now refill languages
            return addLanguagesToRepositoriesData(repositoriesData, githubUserName);
        }
    }
}
