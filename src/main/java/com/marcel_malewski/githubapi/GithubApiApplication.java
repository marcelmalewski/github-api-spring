package com.marcel_malewski.githubapi;

import com.marcel_malewski.githubapi.repository_data.RepositoryData;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@SpringBootApplication
public class GithubApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(GithubApiApplication.class, args);
    }
}
