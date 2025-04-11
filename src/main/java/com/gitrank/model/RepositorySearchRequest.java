package com.gitrank.model;

import lombok.Data;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.List;

@Data
public class RepositorySearchRequest {
    @NotBlank(message = "Search query cannot be empty")
    private String query;
    
    private List<String> languages;
    private List<String> topics;
    
    @Min(value = 1, message = "Page number must be greater than 0")
    private Integer page = 1;
    
    @Min(value = 1, message = "Page size must be greater than 0")
    private Integer size = 10;
    
    @NotNull
    private RankingWeights rankingWeights = new RankingWeights();
} 