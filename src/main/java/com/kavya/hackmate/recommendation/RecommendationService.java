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
                            user.getCity() != null &&
                            currentUser.getCity().equalsIgnoreCase(user.getCity())) {

                        score += 10;
                    }

                    if (currentUser.getCollege() != null &&
                            user.getCollege() != null &&
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

                    score += calculateTextSimilarity(
                            currentUser.getHeadline(),
                            user.getHeadline());

                    score += calculateTextSimilarity(
                            currentUser.getBio(),
                            user.getBio());

                    return RecommendationResponse.builder()
                            .userId(user.getId())
                            .username(user.getUsername())
                            .name(user.getName())
                            .matchScore(score)
                            .college(user.getCollege())
                            .city(user.getCity())
                            .graduationYear(user.getGraduationYear())
                            .bio(user.getBio())
                            .profilePictureUrl(user.getProfilePictureUrl())
                            .githubUrl(user.getGithubUrl())
                            .linkedinUrl(user.getLinkedinUrl())
                            .verified(user.getVerified())
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

    private int calculateTextSimilarity(
            String text1,
            String text2) {

        if (text1 == null || text2 == null) {
            return 0;
        }

        String[] words1 = text1.toLowerCase().split("\\s+");

        String[] words2 = text2.toLowerCase().split("\\s+");

        int matches = 0;

        for (String word1 : words1) {

            for (String word2 : words2) {

                if (word1.equals(word2)) {
                    matches++;
                }
            }
        }

        return matches * 5;
    }
}