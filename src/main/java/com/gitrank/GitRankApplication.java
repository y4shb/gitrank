package com.gitrank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.retry.annotation.EnableRetry;

@SpringBootApplication
@EnableCaching
@EnableRetry
public class GitRankApplication {
    public static void main(String[] args) {
        SpringApplication.run(GitRankApplication.class, args);
    }
}

//todo Add error handling implementation
//
//Implement the web scraping fallback
//
//Add authentication configuration
//
//Implement the exact ranking calculation logic
//
//Add rate limiting implementation