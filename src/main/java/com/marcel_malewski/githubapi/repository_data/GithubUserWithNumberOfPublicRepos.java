package com.marcel_malewski.githubapi.repository_data;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class GithubUserWithNumberOfPublicRepos {
    private int public_repos;
}
