package com.marcel_malewski.githubApi.repository_data;

import com.marcel_malewski.githubApi.GithubServiceLayer;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.http.HttpResponse;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class RepositoryDataServiceTest {

    @Test
    void testIfDataIsReturnedWhenUserExists() throws IOException, URISyntaxException, InterruptedException {
        GithubServiceLayer githubServiceLayer = new GithubServiceLayer();
        GithubServiceLayer githubServiceLayerSpy = spy(githubServiceLayer);

        //creating fake responses so we test this class code only
        String returnForGithubUserWithNumberOfPublicRepos =
                "{\"login\":\"HoddityH\",\"id\":27445275,\"node_id\":\"MDQ6VXNlcjI3NDQ1Mjc1\",\"avatar_url\":\"https://avatars.githubusercontent.com/u/27445275?v=4\",\"gravatar_id\":\"\",\"url\":\"https://api.github.com/users/HoddityH\",\"html_url\":\"https://github.com/HoddityH\",\"followers_url\":\"https://api.github.com/users/HoddityH/followers\",\"following_url\":\"https://api.github.com/users/HoddityH/following{/other_user}\",\"gists_url\":\"https://api.github.com/users/HoddityH/gists{/gist_id}\",\"starred_url\":\"https://api.github.com/users/HoddityH/starred{/owner}{/repo}\",\"subscriptions_url\":\"https://api.github.com/users/HoddityH/subscriptions\",\"organizations_url\":\"https://api.github.com/users/HoddityH/orgs\",\"repos_url\":\"https://api.github.com/users/HoddityH/repos\",\"events_url\":\"https://api.github.com/users/HoddityH/events{/privacy}\",\"received_events_url\":\"https://api.github.com/users/HoddityH/received_events\",\"type\":\"User\",\"site_admin\":false,\"name\":\"Testy\",\"company\":null,\"blog\":\"\",\"location\":null,\"email\":null,\"hireable\":null,\"bio\":null,\"twitter_username\":null,\"public_repos\":3,\"public_gists\":0,\"followers\":0,\"following\":0,\"created_at\":\"2017-04-13T19:18:44Z\",\"updated_at\":\"2022-04-24T12:19:58Z\"}\n";
        @SuppressWarnings("unchecked")
        HttpResponse<String> httpResponseMockWithNumberOfPublicRepos = mock(HttpResponse.class, "httpResponseMock");
        when(httpResponseMockWithNumberOfPublicRepos.body()).thenReturn(returnForGithubUserWithNumberOfPublicRepos);

        String returnForGithubUserWithRepositoriesData =
                "[{\"id\":485020177,\"node_id\":\"R_kgDOHOjSEQ\",\"name\":\"Go\",\"full_name\":\"HoddityH/Go\",\"private\":false,\"owner\":{\"login\":\"HoddityH\",\"id\":27445275,\"node_id\":\"MDQ6VXNlcjI3NDQ1Mjc1\",\"avatar_url\":\"https://avatars.githubusercontent.com/u/27445275?v=4\",\"gravatar_id\":\"\",\"url\":\"https://api.github.com/users/HoddityH\",\"html_url\":\"https://github.com/HoddityH\",\"followers_url\":\"https://api.github.com/users/HoddityH/followers\",\"following_url\":\"https://api.github.com/users/HoddityH/following{/other_user}\",\"gists_url\":\"https://api.github.com/users/HoddityH/gists{/gist_id}\",\"starred_url\":\"https://api.github.com/users/HoddityH/starred{/owner}{/repo}\",\"subscriptions_url\":\"https://api.github.com/users/HoddityH/subscriptions\",\"organizations_url\":\"https://api.github.com/users/HoddityH/orgs\",\"repos_url\":\"https://api.github.com/users/HoddityH/repos\",\"events_url\":\"https://api.github.com/users/HoddityH/events{/privacy}\",\"received_events_url\":\"https://api.github.com/users/HoddityH/received_events\",\"type\":\"User\",\"site_admin\":false},\"html_url\":\"https://github.com/HoddityH/Go\",\"description\":null,\"fork\":false,\"url\":\"https://api.github.com/repos/HoddityH/Go\",\"forks_url\":\"https://api.github.com/repos/HoddityH/Go/forks\",\"keys_url\":\"https://api.github.com/repos/HoddityH/Go/keys{/key_id}\",\"collaborators_url\":\"https://api.github.com/repos/HoddityH/Go/collaborators{/collaborator}\",\"teams_url\":\"https://api.github.com/repos/HoddityH/Go/teams\",\"hooks_url\":\"https://api.github.com/repos/HoddityH/Go/hooks\",\"issue_events_url\":\"https://api.github.com/repos/HoddityH/Go/issues/events{/number}\",\"events_url\":\"https://api.github.com/repos/HoddityH/Go/events\",\"assignees_url\":\"https://api.github.com/repos/HoddityH/Go/assignees{/user}\",\"branches_url\":\"https://api.github.com/repos/HoddityH/Go/branches{/branch}\",\"tags_url\":\"https://api.github.com/repos/HoddityH/Go/tags\",\"blobs_url\":\"https://api.github.com/repos/HoddityH/Go/git/blobs{/sha}\",\"git_tags_url\":\"https://api.github.com/repos/HoddityH/Go/git/tags{/sha}\",\"git_refs_url\":\"https://api.github.com/repos/HoddityH/Go/git/refs{/sha}\",\"trees_url\":\"https://api.github.com/repos/HoddityH/Go/git/trees{/sha}\",\"statuses_url\":\"https://api.github.com/repos/HoddityH/Go/statuses/{sha}\",\"languages_url\":\"https://api.github.com/repos/HoddityH/Go/languages\",\"stargazers_url\":\"https://api.github.com/repos/HoddityH/Go/stargazers\",\"contributors_url\":\"https://api.github.com/repos/HoddityH/Go/contributors\",\"subscribers_url\":\"https://api.github.com/repos/HoddityH/Go/subscribers\",\"subscription_url\":\"https://api.github.com/repos/HoddityH/Go/subscription\",\"commits_url\":\"https://api.github.com/repos/HoddityH/Go/commits{/sha}\",\"git_commits_url\":\"https://api.github.com/repos/HoddityH/Go/git/commits{/sha}\",\"comments_url\":\"https://api.github.com/repos/HoddityH/Go/comments{/number}\",\"issue_comment_url\":\"https://api.github.com/repos/HoddityH/Go/issues/comments{/number}\",\"contents_url\":\"https://api.github.com/repos/HoddityH/Go/contents/{+path}\",\"compare_url\":\"https://api.github.com/repos/HoddityH/Go/compare/{base}...{head}\",\"merges_url\":\"https://api.github.com/repos/HoddityH/Go/merges\",\"archive_url\":\"https://api.github.com/repos/HoddityH/Go/{archive_format}{/ref}\",\"downloads_url\":\"https://api.github.com/repos/HoddityH/Go/downloads\",\"issues_url\":\"https://api.github.com/repos/HoddityH/Go/issues{/number}\",\"pulls_url\":\"https://api.github.com/repos/HoddityH/Go/pulls{/number}\",\"milestones_url\":\"https://api.github.com/repos/HoddityH/Go/milestones{/number}\",\"notifications_url\":\"https://api.github.com/repos/HoddityH/Go/notifications{?since,all,participating}\",\"labels_url\":\"https://api.github.com/repos/HoddityH/Go/labels{/name}\",\"releases_url\":\"https://api.github.com/repos/HoddityH/Go/releases{/id}\",\"deployments_url\":\"https://api.github.com/repos/HoddityH/Go/deployments\",\"created_at\":\"2022-04-24T12:23:07Z\",\"updated_at\":\"2022-04-24T12:23:28Z\",\"pushed_at\":\"2022-04-24T12:23:24Z\",\"git_url\":\"git://github.com/HoddityH/Go.git\",\"ssh_url\":\"git@github.com:HoddityH/Go.git\",\"clone_url\":\"https://github.com/HoddityH/Go.git\",\"svn_url\":\"https://github.com/HoddityH/Go\",\"homepage\":null,\"size\":53,\"stargazers_count\":0,\"watchers_count\":0,\"language\":\"Go\",\"has_issues\":true,\"has_projects\":true,\"has_downloads\":true,\"has_wiki\":true,\"has_pages\":false,\"forks_count\":0,\"mirror_url\":null,\"archived\":false,\"disabled\":false,\"open_issues_count\":0,\"license\":null,\"allow_forking\":true,\"is_template\":false,\"topics\":[],\"visibility\":\"public\",\"forks\":0,\"open_issues\":0,\"watchers\":0,\"default_branch\":\"main\",\"permissions\":{\"admin\":false,\"maintain\":false,\"push\":false,\"triage\":false,\"pull\":true}},{\"id\":485019602,\"node_id\":\"R_kgDOHOjP0g\",\"name\":\"Java\",\"full_name\":\"HoddityH/Java\",\"private\":false,\"owner\":{\"login\":\"HoddityH\",\"id\":27445275,\"node_id\":\"MDQ6VXNlcjI3NDQ1Mjc1\",\"avatar_url\":\"https://avatars.githubusercontent.com/u/27445275?v=4\",\"gravatar_id\":\"\",\"url\":\"https://api.github.com/users/HoddityH\",\"html_url\":\"https://github.com/HoddityH\",\"followers_url\":\"https://api.github.com/users/HoddityH/followers\",\"following_url\":\"https://api.github.com/users/HoddityH/following{/other_user}\",\"gists_url\":\"https://api.github.com/users/HoddityH/gists{/gist_id}\",\"starred_url\":\"https://api.github.com/users/HoddityH/starred{/owner}{/repo}\",\"subscriptions_url\":\"https://api.github.com/users/HoddityH/subscriptions\",\"organizations_url\":\"https://api.github.com/users/HoddityH/orgs\",\"repos_url\":\"https://api.github.com/users/HoddityH/repos\",\"events_url\":\"https://api.github.com/users/HoddityH/events{/privacy}\",\"received_events_url\":\"https://api.github.com/users/HoddityH/received_events\",\"type\":\"User\",\"site_admin\":false},\"html_url\":\"https://github.com/HoddityH/Java\",\"description\":null,\"fork\":false,\"url\":\"https://api.github.com/repos/HoddityH/Java\",\"forks_url\":\"https://api.github.com/repos/HoddityH/Java/forks\",\"keys_url\":\"https://api.github.com/repos/HoddityH/Java/keys{/key_id}\",\"collaborators_url\":\"https://api.github.com/repos/HoddityH/Java/collaborators{/collaborator}\",\"teams_url\":\"https://api.github.com/repos/HoddityH/Java/teams\",\"hooks_url\":\"https://api.github.com/repos/HoddityH/Java/hooks\",\"issue_events_url\":\"https://api.github.com/repos/HoddityH/Java/issues/events{/number}\",\"events_url\":\"https://api.github.com/repos/HoddityH/Java/events\",\"assignees_url\":\"https://api.github.com/repos/HoddityH/Java/assignees{/user}\",\"branches_url\":\"https://api.github.com/repos/HoddityH/Java/branches{/branch}\",\"tags_url\":\"https://api.github.com/repos/HoddityH/Java/tags\",\"blobs_url\":\"https://api.github.com/repos/HoddityH/Java/git/blobs{/sha}\",\"git_tags_url\":\"https://api.github.com/repos/HoddityH/Java/git/tags{/sha}\",\"git_refs_url\":\"https://api.github.com/repos/HoddityH/Java/git/refs{/sha}\",\"trees_url\":\"https://api.github.com/repos/HoddityH/Java/git/trees{/sha}\",\"statuses_url\":\"https://api.github.com/repos/HoddityH/Java/statuses/{sha}\",\"languages_url\":\"https://api.github.com/repos/HoddityH/Java/languages\",\"stargazers_url\":\"https://api.github.com/repos/HoddityH/Java/stargazers\",\"contributors_url\":\"https://api.github.com/repos/HoddityH/Java/contributors\",\"subscribers_url\":\"https://api.github.com/repos/HoddityH/Java/subscribers\",\"subscription_url\":\"https://api.github.com/repos/HoddityH/Java/subscription\",\"commits_url\":\"https://api.github.com/repos/HoddityH/Java/commits{/sha}\",\"git_commits_url\":\"https://api.github.com/repos/HoddityH/Java/git/commits{/sha}\",\"comments_url\":\"https://api.github.com/repos/HoddityH/Java/comments{/number}\",\"issue_comment_url\":\"https://api.github.com/repos/HoddityH/Java/issues/comments{/number}\",\"contents_url\":\"https://api.github.com/repos/HoddityH/Java/contents/{+path}\",\"compare_url\":\"https://api.github.com/repos/HoddityH/Java/compare/{base}...{head}\",\"merges_url\":\"https://api.github.com/repos/HoddityH/Java/merges\",\"archive_url\":\"https://api.github.com/repos/HoddityH/Java/{archive_format}{/ref}\",\"downloads_url\":\"https://api.github.com/repos/HoddityH/Java/downloads\",\"issues_url\":\"https://api.github.com/repos/HoddityH/Java/issues{/number}\",\"pulls_url\":\"https://api.github.com/repos/HoddityH/Java/pulls{/number}\",\"milestones_url\":\"https://api.github.com/repos/HoddityH/Java/milestones{/number}\",\"notifications_url\":\"https://api.github.com/repos/HoddityH/Java/notifications{?since,all,participating}\",\"labels_url\":\"https://api.github.com/repos/HoddityH/Java/labels{/name}\",\"releases_url\":\"https://api.github.com/repos/HoddityH/Java/releases{/id}\",\"deployments_url\":\"https://api.github.com/repos/HoddityH/Java/deployments\",\"created_at\":\"2022-04-24T12:20:27Z\",\"updated_at\":\"2022-04-24T12:21:51Z\",\"pushed_at\":\"2022-04-24T12:21:47Z\",\"git_url\":\"git://github.com/HoddityH/Java.git\",\"ssh_url\":\"git@github.com:HoddityH/Java.git\",\"clone_url\":\"https://github.com/HoddityH/Java.git\",\"svn_url\":\"https://github.com/HoddityH/Java\",\"homepage\":null,\"size\":18,\"stargazers_count\":0,\"watchers_count\":0,\"language\":\"Java\",\"has_issues\":true,\"has_projects\":true,\"has_downloads\":true,\"has_wiki\":true,\"has_pages\":false,\"forks_count\":0,\"mirror_url\":null,\"archived\":false,\"disabled\":false,\"open_issues_count\":0,\"license\":null,\"allow_forking\":true,\"is_template\":false,\"topics\":[],\"visibility\":\"public\",\"forks\":0,\"open_issues\":0,\"watchers\":0,\"default_branch\":\"main\",\"permissions\":{\"admin\":false,\"maintain\":false,\"push\":false,\"triage\":false,\"pull\":true}},{\"id\":485020001,\"node_id\":\"R_kgDOHOjRYQ\",\"name\":\"Java2\",\"full_name\":\"HoddityH/Java2\",\"private\":false,\"owner\":{\"login\":\"HoddityH\",\"id\":27445275,\"node_id\":\"MDQ6VXNlcjI3NDQ1Mjc1\",\"avatar_url\":\"https://avatars.githubusercontent.com/u/27445275?v=4\",\"gravatar_id\":\"\",\"url\":\"https://api.github.com/users/HoddityH\",\"html_url\":\"https://github.com/HoddityH\",\"followers_url\":\"https://api.github.com/users/HoddityH/followers\",\"following_url\":\"https://api.github.com/users/HoddityH/following{/other_user}\",\"gists_url\":\"https://api.github.com/users/HoddityH/gists{/gist_id}\",\"starred_url\":\"https://api.github.com/users/HoddityH/starred{/owner}{/repo}\",\"subscriptions_url\":\"https://api.github.com/users/HoddityH/subscriptions\",\"organizations_url\":\"https://api.github.com/users/HoddityH/orgs\",\"repos_url\":\"https://api.github.com/users/HoddityH/repos\",\"events_url\":\"https://api.github.com/users/HoddityH/events{/privacy}\",\"received_events_url\":\"https://api.github.com/users/HoddityH/received_events\",\"type\":\"User\",\"site_admin\":false},\"html_url\":\"https://github.com/HoddityH/Java2\",\"description\":null,\"fork\":false,\"url\":\"https://api.github.com/repos/HoddityH/Java2\",\"forks_url\":\"https://api.github.com/repos/HoddityH/Java2/forks\",\"keys_url\":\"https://api.github.com/repos/HoddityH/Java2/keys{/key_id}\",\"collaborators_url\":\"https://api.github.com/repos/HoddityH/Java2/collaborators{/collaborator}\",\"teams_url\":\"https://api.github.com/repos/HoddityH/Java2/teams\",\"hooks_url\":\"https://api.github.com/repos/HoddityH/Java2/hooks\",\"issue_events_url\":\"https://api.github.com/repos/HoddityH/Java2/issues/events{/number}\",\"events_url\":\"https://api.github.com/repos/HoddityH/Java2/events\",\"assignees_url\":\"https://api.github.com/repos/HoddityH/Java2/assignees{/user}\",\"branches_url\":\"https://api.github.com/repos/HoddityH/Java2/branches{/branch}\",\"tags_url\":\"https://api.github.com/repos/HoddityH/Java2/tags\",\"blobs_url\":\"https://api.github.com/repos/HoddityH/Java2/git/blobs{/sha}\",\"git_tags_url\":\"https://api.github.com/repos/HoddityH/Java2/git/tags{/sha}\",\"git_refs_url\":\"https://api.github.com/repos/HoddityH/Java2/git/refs{/sha}\",\"trees_url\":\"https://api.github.com/repos/HoddityH/Java2/git/trees{/sha}\",\"statuses_url\":\"https://api.github.com/repos/HoddityH/Java2/statuses/{sha}\",\"languages_url\":\"https://api.github.com/repos/HoddityH/Java2/languages\",\"stargazers_url\":\"https://api.github.com/repos/HoddityH/Java2/stargazers\",\"contributors_url\":\"https://api.github.com/repos/HoddityH/Java2/contributors\",\"subscribers_url\":\"https://api.github.com/repos/HoddityH/Java2/subscribers\",\"subscription_url\":\"https://api.github.com/repos/HoddityH/Java2/subscription\",\"commits_url\":\"https://api.github.com/repos/HoddityH/Java2/commits{/sha}\",\"git_commits_url\":\"https://api.github.com/repos/HoddityH/Java2/git/commits{/sha}\",\"comments_url\":\"https://api.github.com/repos/HoddityH/Java2/comments{/number}\",\"issue_comment_url\":\"https://api.github.com/repos/HoddityH/Java2/issues/comments{/number}\",\"contents_url\":\"https://api.github.com/repos/HoddityH/Java2/contents/{+path}\",\"compare_url\":\"https://api.github.com/repos/HoddityH/Java2/compare/{base}...{head}\",\"merges_url\":\"https://api.github.com/repos/HoddityH/Java2/merges\",\"archive_url\":\"https://api.github.com/repos/HoddityH/Java2/{archive_format}{/ref}\",\"downloads_url\":\"https://api.github.com/repos/HoddityH/Java2/downloads\",\"issues_url\":\"https://api.github.com/repos/HoddityH/Java2/issues{/number}\",\"pulls_url\":\"https://api.github.com/repos/HoddityH/Java2/pulls{/number}\",\"milestones_url\":\"https://api.github.com/repos/HoddityH/Java2/milestones{/number}\",\"notifications_url\":\"https://api.github.com/repos/HoddityH/Java2/notifications{?since,all,participating}\",\"labels_url\":\"https://api.github.com/repos/HoddityH/Java2/labels{/name}\",\"releases_url\":\"https://api.github.com/repos/HoddityH/Java2/releases{/id}\",\"deployments_url\":\"https://api.github.com/repos/HoddityH/Java2/deployments\",\"created_at\":\"2022-04-24T12:22:21Z\",\"updated_at\":\"2022-04-24T12:22:41Z\",\"pushed_at\":\"2022-04-24T12:22:38Z\",\"git_url\":\"git://github.com/HoddityH/Java2.git\",\"ssh_url\":\"git@github.com:HoddityH/Java2.git\",\"clone_url\":\"https://github.com/HoddityH/Java2.git\",\"svn_url\":\"https://github.com/HoddityH/Java2\",\"homepage\":null,\"size\":18,\"stargazers_count\":0,\"watchers_count\":0,\"language\":\"Java\",\"has_issues\":true,\"has_projects\":true,\"has_downloads\":true,\"has_wiki\":true,\"has_pages\":false,\"forks_count\":0,\"mirror_url\":null,\"archived\":false,\"disabled\":false,\"open_issues_count\":0,\"license\":null,\"allow_forking\":true,\"is_template\":false,\"topics\":[],\"visibility\":\"public\",\"forks\":0,\"open_issues\":0,\"watchers\":0,\"default_branch\":\"main\",\"permissions\":{\"admin\":false,\"maintain\":false,\"push\":false,\"triage\":false,\"pull\":true}}]\n";
        @SuppressWarnings("unchecked")
        HttpResponse<String> httpResponseMockWithRepositoriesData = mock(HttpResponse.class, "httpResponseMock");
        when(httpResponseMockWithRepositoriesData.body()).thenReturn(returnForGithubUserWithRepositoriesData);

        String returnForGithubUserWithLanguagesRepository1 = "{\"Go\":16974}";
        @SuppressWarnings("unchecked")
        HttpResponse<String> httpResponseMockWithLanguagesRepository1 = mock(HttpResponse.class, "httpResponseMock");
        when(httpResponseMockWithLanguagesRepository1.body()).thenReturn(returnForGithubUserWithLanguagesRepository1);

        String returnForGithubUserWithLanguagesRepository2 = "{\"Java\":10868}";
        @SuppressWarnings("unchecked")
        HttpResponse<String> httpResponseMockWithLanguagesRepository2 = mock(HttpResponse.class, "httpResponseMock");
        when(httpResponseMockWithLanguagesRepository2.body()).thenReturn(returnForGithubUserWithLanguagesRepository2);

        String returnForGithubUserWithLanguagesRepository3 = "{\"Java\":10868}";
        @SuppressWarnings("unchecked")
        HttpResponse<String> httpResponseMockWithLanguagesRepository3 = mock(HttpResponse.class, "httpResponseMock");
        when(httpResponseMockWithLanguagesRepository3.body()).thenReturn(returnForGithubUserWithLanguagesRepository3);

        // What githubServiceLayer will respond for each request
        Mockito.doReturn(httpResponseMockWithNumberOfPublicRepos).when(githubServiceLayerSpy).getResponseFromLink("https://api.github.com/users/HoddityH");
        Mockito.doReturn(httpResponseMockWithRepositoriesData).when(githubServiceLayerSpy).getResponseFromLink("https://api.github.com/users/HoddityH/repos?per_page=100");
        Mockito.doReturn(httpResponseMockWithLanguagesRepository1).when(githubServiceLayerSpy).getResponseFromLink("https://api.github.com/repos/HoddityH/Go/languages");
        Mockito.doReturn(httpResponseMockWithLanguagesRepository2).when(githubServiceLayerSpy).getResponseFromLink("https://api.github.com/repos/HoddityH/Java/languages");
        Mockito.doReturn(httpResponseMockWithLanguagesRepository3).when(githubServiceLayerSpy).getResponseFromLink("https://api.github.com/repos/HoddityH/Java2/languages");

        RepositoryDataService repositoryDataService = new RepositoryDataService(githubServiceLayerSpy);

        List<RepositoryData> result = repositoryDataService.getRepositoriesDataOfGithubUser("HoddityH");
        String expectedResult =
                "[RepositoryData(name=Go, languages={Go=16974}), RepositoryData(name=Java, languages={Java=10868}), RepositoryData(name=Java2, languages={Java=10868})]";
        assertEquals(expectedResult, result.toString());
    }
}