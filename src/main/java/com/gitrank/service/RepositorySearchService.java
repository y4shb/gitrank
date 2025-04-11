package com.gitrank.service;

import com.gitrank.model.RepositorySearchRequest;
import com.gitrank.model.GitHubSearchResponse;
import org.springframework.cache.annotation.Cacheable;

public interface RepositorySearchService {
    @Cacheable(value = "repositorySearch", key = "#request.hashCode()")
    GitHubSearchResponse searchRepositories(RepositorySearchRequest request);
} 