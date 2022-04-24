package com.marcel_malewski.githubapi.repository_data;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RepositoryDataServiceTest {
    RepositoryDataService repositoryDataService;

    @BeforeEach
    public void before(){
        repositoryDataService = new RepositoryDataService();
    }

    @Test
    void getRepositoriesDataOfGithubUserWhenUserExists() throws IOException, URISyntaxException, InterruptedException {
        List<RepositoryData> result = repositoryDataService.getRepositoriesDataOfGithubUser("HoddityH");
        String expectedResult =
                "[RepositoryData(name=Go, languages={Go=16974}), RepositoryData(name=Java, languages={Java=10868}), RepositoryData(name=Java2, languages={Java=10868})]";
        assertEquals(expectedResult, result.toString());
    }

    @Test
    void getRepositoriesDataOfGithubUserWhenUserDontExist() {
        Exception exception = assertThrows(ResponseStatusException.class, () -> {
            List<RepositoryData> result = repositoryDataService.getRepositoriesDataOfGithubUser("");
        });

        String expectedMessage = "GithubUser not found";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }
}