package com.marcel_malewski.githubapi.github_user_data;

import com.marcel_malewski.githubapi.repository_data.RepositoryData;
import com.marcel_malewski.githubapi.repository_data.RepositoryDataService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


class GithubUserDataServiceTest {

    RepositoryDataService repositoryDataServiceMock;

    @BeforeEach
    public void before(){
        repositoryDataServiceMock = mock(RepositoryDataService.class);
    }

    @Test
    void getGithubUserDataWhenUserExists() throws IOException, URISyntaxException, InterruptedException {
        List<RepositoryData> returnFromGetRepositoriesDataOfGithubUser = new ArrayList<>() {{
            add(new RepositoryData("Go", Map.of("Go", 16974)));
            add(new RepositoryData("Java", Map.of("Java", 10868)));
            add(new RepositoryData("Java2", Map.of("Java", 10868)));
        }};

        when(repositoryDataServiceMock.getRepositoriesDataOfGithubUser(anyString())).thenReturn(returnFromGetRepositoriesDataOfGithubUser);

        GithubUserDataService githubUserDataService = new GithubUserDataService(repositoryDataServiceMock);
        GithubUserData result = githubUserDataService.getGithubUserData("HoddityH");
        String expectedResult =
                "GithubUserData(login=HoddityH, name=Testy, bio=null, languages={Java=21736, Go=16974})";
        assertEquals(expectedResult, result.toString());
    }

    @Test
    void getGithubUserDataWhenUserDontExist() {
        GithubUserDataService githubUserDataService = new GithubUserDataService(repositoryDataServiceMock);

        Exception exception = assertThrows(ResponseStatusException.class, () -> {
            GithubUserData result = githubUserDataService.getGithubUserData("");
        });

        String expectedMessage = "GithubUser not found";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }
}