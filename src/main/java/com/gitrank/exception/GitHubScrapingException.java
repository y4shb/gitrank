package com.gitrank.exception;

public class GitHubScrapingException extends RuntimeException {
    public GitHubScrapingException(String message, Throwable cause) {
        super(message, cause);
    }
}
