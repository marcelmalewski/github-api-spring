package com.marcel_malewski.githubapi.github_user_data;

import com.marcel_malewski.githubapi.repository_data.RepositoryDataService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Map;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.ArgumentMatchers.anyString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class GithubUserDataControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GithubUserDataService githubUserDataService;

    @Test
    void getGithubUserData() throws Exception {
        GithubUserData returnFromGetGithubUserData =
                new GithubUserData("HoddityH", "Testy", null, null);

        Mockito.when(githubUserDataService.getGithubUserData(anyString())).thenReturn(returnFromGetGithubUserData);
        String expectedResult =
                "{\"login\":\"HoddityH\",\"name\":\"Testy\",\"bio\":null,\"languages\":null}";

        mockMvc.perform(get("/api/githubUserData/HoddityH")
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk())
                        .andExpect(content().string(expectedResult));
    }
}