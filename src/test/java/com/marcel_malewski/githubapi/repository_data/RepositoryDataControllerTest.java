package com.marcel_malewski.githubApi.repository_data;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.ArgumentMatchers.anyString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class RepositoryDataControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RepositoryDataService repositoryDataService;

    @Test
    void testIfEndpointWorksWhenUserExists() throws Exception {
        List<RepositoryData> returnFromGetRepositoriesDataOfGithubUser = new ArrayList<>() {{
            add(new RepositoryData("Go", Map.of("Go", 16974)));
            add(new RepositoryData("Java", Map.of("Java", 10868)));
            add(new RepositoryData("Java2", Map.of("Java", 10868)));
        }};

        Mockito.when(repositoryDataService.getRepositoriesDataOfGithubUser(anyString())).thenReturn(returnFromGetRepositoriesDataOfGithubUser);
        String expectedResult = "[{\"name\":\"Go\",\"languages\":{\"Go\":16974}},{\"name\":\"Java\",\"languages\":{\"Java\":10868}},{\"name\":\"Java2\",\"languages\":{\"Java\":10868}}]";

        mockMvc.perform(get("/api/repositoriesData/HoddityH")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(content().string(expectedResult));
    }
}