package com.marcel_malewski.githubApi.github_user_data;

import com.marcel_malewski.githubApi.GithubServiceLayer;
import com.marcel_malewski.githubApi.repository_data.RepositoryData;
import com.marcel_malewski.githubApi.repository_data.RepositoryDataService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;


class GithubUserDataServiceTest {
    private GithubServiceLayer githubServiceLayerSpy;
    private String toReturnFromResponseBody;

    @BeforeEach
    void before() {
        GithubServiceLayer githubServiceLayer = new GithubServiceLayer();
        githubServiceLayerSpy = spy(githubServiceLayer);
        toReturnFromResponseBody =
                "{\"login\":\"HoddityH\",\"id\":27445275,\"node_id\":\"MDQ6VXNlcjI3NDQ1Mjc1\",\"avatar_url\":\"https://avatars.githubusercontent.com/u/27445275?v=4\",\"gravatar_id\":\"\",\"url\":\"https://api.github.com/users/HoddityH\",\"html_url\":\"https://github.com/HoddityH\",\"followers_url\":\"https://api.github.com/users/HoddityH/followers\",\"following_url\":\"https://api.github.com/users/HoddityH/following{/other_user}\",\"gists_url\":\"https://api.github.com/users/HoddityH/gists{/gist_id}\",\"starred_url\":\"https://api.github.com/users/HoddityH/starred{/owner}{/repo}\",\"subscriptions_url\":\"https://api.github.com/users/HoddityH/subscriptions\",\"organizations_url\":\"https://api.github.com/users/HoddityH/orgs\",\"repos_url\":\"https://api.github.com/users/HoddityH/repos\",\"events_url\":\"https://api.github.com/users/HoddityH/events{/privacy}\",\"received_events_url\":\"https://api.github.com/users/HoddityH/received_events\",\"type\":\"User\",\"site_admin\":false,\"name\":\"Testy\",\"company\":null,\"blog\":\"\",\"location\":null,\"email\":null,\"hireable\":null,\"bio\":null,\"twitter_username\":null,\"public_repos\":3,\"public_gists\":0,\"followers\":0,\"following\":0,\"created_at\":\"2017-04-13T19:18:44Z\",\"updated_at\":\"2022-04-24T12:19:58Z\"}\n";
    }
    @Test
    void testIfDataIsReturnedWhenUserExists() throws IOException, URISyntaxException, InterruptedException {
        @SuppressWarnings("unchecked")
        HttpResponse<String> httpResponseMock = mock(HttpResponse.class, "httpResponseMock");
        when(httpResponseMock.body()).thenReturn(toReturnFromResponseBody);

        Mockito.doReturn(httpResponseMock).when(githubServiceLayerSpy).getResponseFromLink("api/githubUserData/HoddityH");

        RepositoryDataService repositoryDataServiceMock = mock(RepositoryDataService.class);
        List<RepositoryData> returnFromGetRepositoriesDataOfGithubUser = new ArrayList<>();

        when(repositoryDataServiceMock.getRepositoriesDataOfGithubUser(anyString())).thenReturn(returnFromGetRepositoriesDataOfGithubUser);

        GithubUserDataService githubUserDataService = new GithubUserDataService(repositoryDataServiceMock, githubServiceLayerSpy);
        GithubUserData result = githubUserDataService.getGithubUserData("HoddityH");
        String expectedResult =
                "GithubUserData(login=HoddityH, name=Testy, bio=null, languages={})";
        assertEquals(expectedResult, result.toString());
    }

    @Test
    void testIfLanguagesAreAggregatedWhenUserExists() throws IOException, URISyntaxException, InterruptedException {
        @SuppressWarnings("unchecked")
        HttpResponse<String> httpResponseMock = mock(HttpResponse.class, "httpResponseMock");
        when(httpResponseMock.body()).thenReturn(toReturnFromResponseBody);

        Mockito.doReturn(httpResponseMock).when(githubServiceLayerSpy).getResponseFromLink("api/githubUserData/HoddityH");

        RepositoryDataService repositoryDataServiceMock = mock(RepositoryDataService.class);
        List<RepositoryData> returnFromGetRepositoriesDataOfGithubUser = new ArrayList<>() {{
            add(new RepositoryData("Go", Map.of("Go", 16974)));
            add(new RepositoryData("Java", Map.of("Java", 10868)));
            add(new RepositoryData("Java2", Map.of("Java", 10868)));
        }};

        when(repositoryDataServiceMock.getRepositoriesDataOfGithubUser(anyString())).thenReturn(returnFromGetRepositoriesDataOfGithubUser);

        GithubUserDataService githubUserDataService = new GithubUserDataService(repositoryDataServiceMock, githubServiceLayerSpy);
        GithubUserData result = githubUserDataService.getGithubUserData("HoddityH");

        Map<String, Integer> expectedResult = Map.of("Go", 16974, "Java", 21736);
        assertEquals(result.getLanguages(), expectedResult);
    }
}