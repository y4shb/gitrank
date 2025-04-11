package com.gitrank.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GitHubApiRepository {
    private Long id;
    private String name;
    
    @JsonProperty("full_name")
    private String fullName;
    
    private String description;
    
    @JsonProperty("stargazers_count")
    private Integer stargazersCount;
    
    @JsonProperty("forks_count")
    private Integer forksCount;
    
    private String language;
    private List<String> topics;
    
    @JsonProperty("updated_at")
    private LocalDateTime updatedAt;
} 