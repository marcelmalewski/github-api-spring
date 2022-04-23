package com.marcel_malewski.githubapi.repository_data;

import com.fasterxml.jackson.core.type.TypeReference;
import com.marcel_malewski.githubapi.GithubService;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Map;

@Service
public class RepositoryDataService extends GithubService {
    private List<RepositoryData> addLanguagesToRepositoriesData(List<RepositoryData> repositoriesData, String githubUserName) {
        repositoriesData.forEach(repositoryData -> {
            //first create link to get languages of specific repository
            String languagesUrl = String.format("https://api.github.com/repos/%s/%s/languages", githubUserName, repositoryData.getName());

            try {
                //get languages as json
                HttpResponse<String> response = getResponseFromLink(languagesUrl);
                //convert json to map of languages
                Map<String, Integer> mappedLanguages = this.mapper.readValue(response.body(), new TypeReference<>() {});
                //add hashmap with languages to specific repositoryData
                repositoryData.setLanguages(mappedLanguages);
            } catch (URISyntaxException | IOException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        return repositoriesData;
    }
    public List<RepositoryData> getRepositoriesDataOfGithubUser(String githubUserName) throws IOException, URISyntaxException, InterruptedException {
        String repositoriesUrl = String.format("https://api.dsgithub.com/users/%s/repos", githubUserName);
        //Now with our link and githubUserName get response with user data
        HttpResponse<String> response = getResponseFromLink(repositoriesUrl);
        //then convert body to object
        List<RepositoryData> repositoriesData = this.mapper.readValue(response.body(), new TypeReference<>() {});
        //now refill languages
        return addLanguagesToRepositoriesData(repositoriesData, githubUserName);
    }
}
