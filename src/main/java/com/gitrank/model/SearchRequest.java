package com.gitrank.model;

import lombok.Data;

@Data
public class SearchRequest {
    private String query;
    private String language;
    private String topic;
    private int page = 0;
    private int size = 10;
    private RankingWeights weights = new RankingWeights();
}

@Data
class RankingWeights {
    private double stars = 0.5;
    private double activity = 0.3;
    private double contributors = 0.2;
}