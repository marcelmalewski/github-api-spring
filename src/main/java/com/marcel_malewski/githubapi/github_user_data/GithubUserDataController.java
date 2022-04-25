package com.marcel_malewski.githubApi.github_user_data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URISyntaxException;

@RestController
@RequestMapping(path="api/githubUserData/{githubUserName}")
public class GithubUserDataController {
    private final GithubUserDataService githubUserDataService;

    @Autowired
    public GithubUserDataController(GithubUserDataService githubUserDataService) {
        this.githubUserDataService = githubUserDataService;
    }

    @GetMapping
    public GithubUserData getGithubUserData(@PathVariable String githubUserName) throws IOException, InterruptedException, URISyntaxException {
        return githubUserDataService.getGithubUserData(githubUserName);
    }
}
