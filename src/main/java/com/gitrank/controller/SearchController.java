package com.gitrank.controller;

import com.gitrank.model.GitHubSearchResponse;
import com.gitrank.model.RepositorySearchRequest;
import com.gitrank.service.RepositorySearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/search")
public class SearchController {

    @Autowired
    private RepositorySearchService searchService;

    @PostMapping
    public ResponseEntity<GitHubSearchResponse> searchRepositories(@RequestBody RepositorySearchRequest request) {
        return ResponseEntity.ok(searchService.searchRepositories(request));
    }
}