package com.gitrank.client;

import com.gitrank.exception.GitHubApiException;
import com.gitrank.model.GitHubApiRepository;
import com.gitrank.model.GitHubSearchResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class GitHubApiClient implements GithubClient {
    
    @Autowired
    private RestTemplate gitHubRestTemplate;
    
    @Value("${github.api.token}")
    private String githubToken;
    @SuppressWarnings("null")
    @Override
    public List<GitHubApiRepository> searchRepositories(String query, List<String> languages, 
                                                   List<String> topics, int page, int size) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + githubToken);
        headers.set("Accept", "application/vnd.github.v3+json");
        
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString("/search/repositories")
                .queryParam("q", buildSearchQuery(query, languages, topics))
                .queryParam("page", page)
                .queryParam("per_page", size)
                .queryParam("sort", "stars")
                .queryParam("order", "desc");
        
        HttpEntity<String> entity = new HttpEntity<>(headers);
        
        try {
            ResponseEntity<GitHubSearchResponse> response = gitHubRestTemplate.exchange(
                builder.toUriString(),
                HttpMethod.GET,
                entity,
                GitHubSearchResponse.class
            );
            
            return response.getBody().getItems().stream()
                .map(this::mapToRepository)
                .collect(Collectors.toList());
        } catch (Exception e) {
            throw new GitHubApiException("Failed to fetch repositories from GitHub API", e);
        }
    }
    
    private String buildSearchQuery(String query, List<String> languages, List<String> topics) {
        StringBuilder searchQuery = new StringBuilder(query);
        
        if (languages != null && !languages.isEmpty()) {
            searchQuery.append(" language:").append(String.join(" language:", languages));
        }
        
        if (topics != null && !topics.isEmpty()) {
            searchQuery.append(" topic:").append(String.join(" topic:", topics));
        }
        
        return searchQuery.toString();
    }
    
    private GitHubApiRepository mapToRepository(GitHubApiRepository apiRepo) {
        return GitHubApiRepository.builder()
            .id(apiRepo.getId())
            .name(apiRepo.getName())
            .fullName(apiRepo.getFullName())
            .description(apiRepo.getDescription())
            .stargazersCount(apiRepo.getStargazersCount())
            .forksCount(apiRepo.getForksCount())
            .language(apiRepo.getLanguage())
            .topics(apiRepo.getTopics())
            .updatedAt(apiRepo.getUpdatedAt())
            .build();
    }
} 