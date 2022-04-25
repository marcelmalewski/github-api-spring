package com.marcel_malewski.githubApi.repository_data;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RepositoryDataServiceTest {

    @Test
    void testIfDataIsReturnedWhenUserExists() throws IOException, URISyntaxException, InterruptedException {
        RepositoryDataService repositoryDataService = new RepositoryDataService();

        List<RepositoryData> result = repositoryDataService.getRepositoriesDataOfGithubUser("HoddityH");
        String expectedResult =
                "[RepositoryData(name=Go, languages={Go=16974}), RepositoryData(name=Java, languages={Java=10868}), RepositoryData(name=Java2, languages={Java=10868})]";
        assertEquals(expectedResult, result.toString());
    }
}