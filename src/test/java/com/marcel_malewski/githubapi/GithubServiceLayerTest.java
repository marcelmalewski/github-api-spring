package com.marcel_malewski.githubapi;

import org.junit.jupiter.api.Test;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.http.HttpResponse;

import static org.junit.jupiter.api.Assertions.*;

class GithubServiceLayerTest {

    @Test
    void getStatusOkWhenUserExists() throws URISyntaxException, IOException, InterruptedException {
        GithubServiceLayer githubServiceLayer = new GithubServiceLayer();

        HttpResponse<String> result = githubServiceLayer.getResponseFromLink("https://api.github.com/users/HoddityH");
        int expectedResult = 200;

        assertEquals(expectedResult, result.statusCode());
    }

    @Test
    void getUserNotFoundWhenUserNotFound() {
        GithubServiceLayer githubServiceLayer = new GithubServiceLayer();

        Exception exception = assertThrows(ResponseStatusException.class, () -> {
            HttpResponse<String> result = githubServiceLayer.getResponseFromLink("https://api.github.com/users/");
        });

        String expectedMessage = "GithubUser not found";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }
}