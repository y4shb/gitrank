package com.gitrank.service;

import com.gitrank.client.GithubClient;
import com.gitrank.client.GithubClientFactory;
import com.gitrank.exception.GitHubApiException;
import com.gitrank.model.RepositorySearchRequest;
import com.gitrank.model.GitHubApiRepository;
import com.gitrank.model.GitHubSearchResponse;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RepositorySearchServiceImpl implements RepositorySearchService {
    
    @Autowired
    private GithubClientFactory clientFactory;
    
    @Override
    public GitHubSearchResponse searchRepositories(RepositorySearchRequest request) {
        try {
            GithubClient client = clientFactory.getClient(true);
            List<GitHubApiRepository> repositories = client.searchRepositories(
                request.getQuery(),
                request.getLanguages(),
                request.getTopics(),
                request.getPage(),
                request.getSize()
            );
            
            return new GitHubSearchResponse(repositories);
        } catch (GitHubApiException e) {
            // Fallback to scraping if API fails
            GithubClient client = clientFactory.getClient(false);
            List<GitHubApiRepository> repositories = client.searchRepositories(
                request.getQuery(),
                request.getLanguages(),
                request.getTopics(),
                request.getPage(),
                request.getSize()
            );
            
            return new GitHubSearchResponse(repositories);
        }
    }
} 