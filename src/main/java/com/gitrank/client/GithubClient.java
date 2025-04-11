package com.gitrank.client;

import com.gitrank.model.GitHubApiRepository;

import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;

import java.util.List;

public interface GithubClient {
    @Retryable(maxAttempts = 3, backoff = @Backoff(delay = 1000, multiplier = 2))
    List<GitHubApiRepository> searchRepositories(String query, List<String> languages, 
                                            List<String> topics, int page, int size);
}