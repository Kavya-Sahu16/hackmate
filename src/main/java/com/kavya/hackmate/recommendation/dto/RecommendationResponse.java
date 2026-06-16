package com.kavya.hackmate.recommendation.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class RecommendationResponse {

    private Long userId;

    private String username;

    private String name;

    private Integer matchScore;
}