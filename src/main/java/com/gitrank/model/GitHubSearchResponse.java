package com.gitrank.model;

import lombok.Data;
import java.util.List;

@Data
public class GitHubSearchResponse {
    public GitHubSearchResponse(List<GitHubApiRepository> repositories) {
        //TODO 
    }
    private List<GitHubApiRepository> items;
    private Integer totalCount;
} 