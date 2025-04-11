package com.gitrank.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriBuilderFactory;

@Configuration
public class GitHubConfig {
    
    @Value("${github.api.base-url}")
    private String githubApiBaseUrl;
    
    @Value("${github.api.token}")
    private String githubToken;
    
    @Bean
    public RestTemplate gitHubRestTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setUriTemplateHandler(new DefaultUriBuilderFactory(githubApiBaseUrl));
        return restTemplate;
    }
} 