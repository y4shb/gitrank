package com.gitrank.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.Data;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(GitHubApiException.class)
    public ResponseEntity<ErrorResponse> handleGitHubApiException(GitHubApiException ex) {
        logger.error("GitHub API error: {}", ex.getMessage());
        return new ResponseEntity<>(
            new ErrorResponse("GitHub API error", ex.getMessage()),
            HttpStatus.SERVICE_UNAVAILABLE
        );
    }

    @ExceptionHandler(GitHubScrapingException.class)
    public ResponseEntity<ErrorResponse> handleGitHubScrapingException(GitHubScrapingException ex) {
        logger.error("GitHub scraping error: {}", ex.getMessage());
        return new ResponseEntity<>(
            new ErrorResponse("GitHub scraping error", ex.getMessage()),
            HttpStatus.SERVICE_UNAVAILABLE
        );
    }
}

@Data
class ErrorResponse {
    private String error;
    private String message;

    public ErrorResponse(String error, String message) {
        this.error = error;
        this.message = message;
    }
} 