package com.marcel_malewski.githubapi.repository_data;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class RepositoryDataWithLanguages {
    private String name;
    private String languages;

    public RepositoryDataWithLanguages(String name, String languages) {
        this.name = name;
        this.languages = languages;
    }
}
