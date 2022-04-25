package com.marcel_malewski.githubApi.github_user_data;

import com.marcel_malewski.githubApi.repository_data.RepositoryData;
import com.marcel_malewski.githubApi.repository_data.RepositoryDataService;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


class GithubUserDataServiceTest {

    @Test
    void testIfDataIsReturnedWhenUserExists() throws IOException, URISyntaxException, InterruptedException {
        RepositoryDataService repositoryDataServiceMock = mock(RepositoryDataService.class);
        List<RepositoryData> returnFromGetRepositoriesDataOfGithubUser = new ArrayList<>();

        when(repositoryDataServiceMock.getRepositoriesDataOfGithubUser(anyString())).thenReturn(returnFromGetRepositoriesDataOfGithubUser);

        GithubUserDataService githubUserDataService = new GithubUserDataService(repositoryDataServiceMock);
        GithubUserData result = githubUserDataService.getGithubUserData("HoddityH");
        String expectedResult =
                "GithubUserData(login=HoddityH, name=Testy, bio=null, languages={})";
        assertEquals(expectedResult, result.toString());
    }

    @Test
    void testIfLanguagesAreAggregatedWhenUserExists() throws IOException, URISyntaxException, InterruptedException {
        RepositoryDataService repositoryDataServiceMock = mock(RepositoryDataService.class);
        List<RepositoryData> returnFromGetRepositoriesDataOfGithubUser = new ArrayList<>() {{
            add(new RepositoryData("Go", Map.of("Go", 16974)));
            add(new RepositoryData("Java", Map.of("Java", 10868)));
            add(new RepositoryData("Java2", Map.of("Java", 10868)));
        }};

        when(repositoryDataServiceMock.getRepositoriesDataOfGithubUser(anyString())).thenReturn(returnFromGetRepositoriesDataOfGithubUser);

        GithubUserDataService githubUserDataService = new GithubUserDataService(repositoryDataServiceMock);
        GithubUserData result = githubUserDataService.getGithubUserData("HoddityH");

        Map<String, Integer> expectedResult = Map.of("Go", 16974, "Java", 21736);
        assertEquals(result.getLanguages(), expectedResult);
    }
}