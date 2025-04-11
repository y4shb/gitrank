package com.gitrank.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GithubClientFactory {
    
    @Autowired
    private GitHubApiClient apiClient;
    
    @Autowired
    private GitHubApiClient scrapingClient;
    
    public GitHubApiClient getClient(boolean useApi) {
        return useApi ? apiClient : scrapingClient;
    }
} 