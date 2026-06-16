package com.kavya.hackmate.recommendation;

import com.kavya.hackmate.recommendation.dto.RecommendationResponse;
import com.kavya.hackmate.recommendation.dto.SkillExtractionRequest;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/recommendations")
@RequiredArgsConstructor
public class RecommendationController {

    private final RecommendationService recommendationService;

    @GetMapping
    public List<RecommendationResponse> getRecommendations(
            Authentication authentication) {

        return recommendationService.getRecommendations(
                authentication.getName());
    }

    @PostMapping("/extract-skills")
    public List<String> extractSkills(
            @RequestBody SkillExtractionRequest request) {

        return recommendationService.extractSkills(
                request.getText());
    }
}
