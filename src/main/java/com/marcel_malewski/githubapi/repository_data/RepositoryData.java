package com.marcel_malewski.githubapi.repository_data;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Map;

@Getter
@Setter
@ToString
public class RepositoryData {
    private String name;
    private Map<String, Integer> languages;

    public RepositoryData(String name, Map<String, Integer> languages) {
        this.name = name;
        this.languages = languages;
    }

    public RepositoryData() {}
}
