package com.gitrank.controller;

import com.gitrank.model.SearchRequest;
import com.gitrank.model.SearchResult;
import com.gitrank.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/search")
public class SearchController {

    @Autowired
    private SearchService searchService;

    @PostMapping
    public ResponseEntity<SearchResult> searchRepositories(@RequestBody SearchRequest request) {
        return ResponseEntity.ok(searchService.searchRepositories(request));
    }
}