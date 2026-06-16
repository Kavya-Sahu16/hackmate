package com.kavya.hackmate.recommendation;

import com.kavya.hackmate.recommendation.dto.RecommendationResponse;
import com.kavya.hackmate.user.User;
import com.kavya.hackmate.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RecommendationService {

    private final UserRepository userRepository;

    private static final List<String> KNOWN_SKILLS = List.of(
            "Java",
            "Spring Boot",
            "React",
            "Machine Learning",
            "Python",
            "Docker",
            "AWS");

    public List<RecommendationResponse> getRecommendations(
            String username) {

        User currentUser = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        List<User> users = userRepository.findAll();

        return users.stream()
                .filter(user -> !user.getId().equals(currentUser.getId()))
                .map(user -> {

                    int score = 0;
                    if (currentUser.getCity() != null &&
                            currentUser.getCity().equalsIgnoreCase(user.getCity())) {

                        score += 10;
                    }

                    if (currentUser.getCollege() != null &&
                            currentUser.getCollege().equalsIgnoreCase(user.getCollege())) {

                        score += 10;
                    }

                    if (currentUser.getGraduationYear() != null &&
                            currentUser.getGraduationYear()
                                    .equals(user.getGraduationYear())) {

                        score += 10;
                    }

                    int matchingSkills = 0;
                    for (var skill : currentUser.getSkills()) {
                        if (user.getSkills().contains(skill)) {
                            matchingSkills++;
                        }
                    }
                    score += matchingSkills * 20;

                    int matchingInterests = 0;
                    for (var interest : currentUser.getInterests()) {
                        if (user.getInterests().contains(interest)) {
                            matchingInterests++;
                        }
                    }
                    score += matchingInterests * 15;

                    return RecommendationResponse.builder()
                            .userId(user.getId())
                            .username(user.getUsername())
                            .name(user.getName())
                            .matchScore(score)
                            .build();
                })
                .sorted(
                        Comparator.comparing(
                                RecommendationResponse::getMatchScore).reversed())
                .toList();
    }

    public List<String> extractSkills(String text) {

        return KNOWN_SKILLS.stream()
                .filter(skill -> text.toLowerCase()
                        .contains(skill.toLowerCase()))
                .toList();

    }
}