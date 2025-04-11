package com.gitrank.client;

import java.util.List;

// import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Component;
// import org.springframework.web.client.RestTemplate;

import com.gitrank.model.GitHubApiRepository;

@Component
public class GithubClientImpl implements GithubClient {

    @Value("${github.api.url}")
    private String apiUrl;

    @Value("${github.api.token}")
    private String apiToken;

    // @Autowired
    // private RestTemplate restTemplate;

    @Retryable(maxAttempts = 3, backoff = @Backoff(delay = 1000, multiplier = 2))
    @Cacheable(value = "githubRepos", unless = "#result == null")
    public List<GitHubApiRepository> searchGitHubApiRepositories(String query, List<String> languages, 
                                                    List<String> topics, int page, int size) {
        // Implementation calling GitHub API
        // Handle rate limits and throw RateLimitException when exceeded
        return null; // Temporary return to fix compilation error
    }

    @Override
    public List<GitHubApiRepository> searchRepositories(String query, List<String> languages, List<String> topics,
            int page, int size) {
        // TODO
        throw new UnsupportedOperationException("Unimplemented method 'searchRepositories'");
    }
}