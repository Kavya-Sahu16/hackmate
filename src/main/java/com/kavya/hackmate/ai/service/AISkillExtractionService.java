package com.kavya.hackmate.ai.service;

import com.kavya.hackmate.ai.client.GeminiClient;
import com.kavya.hackmate.ai.dto.SkillExtractionResponse;
import com.kavya.hackmate.skill.Skill;
import com.kavya.hackmate.skill.SkillRepository;
import com.kavya.hackmate.user.User;
import com.kavya.hackmate.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AISkillExtractionService {

    private final GeminiClient geminiClient;
    private final SkillRepository skillRepository;
    private final UserRepository userRepository;

    public SkillExtractionResponse extractSkills(String bio) {

        String result = geminiClient.extractSkills(bio);

        List<String> skills = Arrays.stream(result.split(","))
                .map(String::trim)
                .filter(s -> !s.isBlank())
                .toList();

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String username = authentication.getName();

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        for (String skillName : skills) {

            Skill skill = skillRepository.findByName(skillName)
                    .orElseGet(() -> {

                        Skill newSkill = Skill.builder()
                                .name(skillName)
                                .category("AI Extracted")
                                .build();

                        return skillRepository.save(newSkill);
                    });

            user.getSkills().add(skill);
        }

        userRepository.save(user);

        return SkillExtractionResponse.builder()
                .skills(skills)
                .build();
    }
}