package com.gitrank.client;

import com.gitrank.model.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class GitHubClient {

    @Value("${github.api.url}")
    private String apiUrl;

    @Value("${github.api.token}")
    private String apiToken;

    @Autowired
    private RestTemplate restTemplate;

    @Retryable(value = {RateLimitException.class}, maxAttempts = 3, backoff = @Backoff(delay = 1000))
    @Cacheable(value = "githubRepos", unless = "#result == null")
    public List<Repository> searchRepositories(SearchRequest request) {
        // Implementation calling GitHub API
        // Handle rate limits and throw RateLimitException when exceeded
    }
}