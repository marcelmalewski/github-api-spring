package com.marcel_malewski.githubapi.github_user_data;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Map;

@Getter
@Setter
@ToString
public class GithubUserData {
    private String login;
    private String name;
    private String bio;
    private Map<String, Integer> languages;

    public GithubUserData(String login, String name, String bio, Map<String, Integer> languages) {
        this.login = login;
        this.name = name;
        this.bio = bio;
        this.languages = languages;
    }

    public GithubUserData() {
    }
}
