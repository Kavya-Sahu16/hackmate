package com.kavya.hackmate.recommendation.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RecommendationResponse {

    private Long userId;

    private String username;

    private String name;

    private Integer matchScore;

    private String college;

    private String city;

    private Integer graduationYear;

    private String bio;

    private String profilePictureUrl;

    private String githubUrl;

    private String linkedinUrl;

    private Boolean verified;
}