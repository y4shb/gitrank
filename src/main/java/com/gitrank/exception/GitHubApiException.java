package com.gitrank.exception;

public class GitHubApiException extends RuntimeException {
    public GitHubApiException(String message, Throwable cause) {
        super(message, cause);
    }
}

