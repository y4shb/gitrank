package com.gitrank.model;

import lombok.Data;

@Data
public class RankingWeights {
    private double starsWeight = 0.4;
    private double activityWeight = 0.3;
    private double forksWeight = 0.2;
    private double contributorsWeight = 0.1;
} 