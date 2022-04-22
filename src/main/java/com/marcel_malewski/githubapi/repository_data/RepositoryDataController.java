package com.marcel_malewski.githubapi.repository_data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping(path="api/repositoryData/{githubUserName}")
public class RepositoryDataController {

    private final RepositoryDataService repositoryDataService;

    @Autowired
    public RepositoryDataController(RepositoryDataService repositoryDataService) {
        this.repositoryDataService = repositoryDataService;
    }

    @GetMapping
    @ResponseBody
    public List<RepositoryDataWithLanguages> getRepositoriesDataOfGithubUser(@PathVariable String githubUserName) throws URISyntaxException, IOException, InterruptedException {
        return repositoryDataService.getRepositoriesDataOfGithubUser(githubUserName);
    }
}
