package com.kavya.hackmate.ai.controller;

import com.kavya.hackmate.ai.dto.SkillExtractionResponse;
import com.kavya.hackmate.ai.service.AISkillExtractionService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ai")
public class AISkillExtractionController {

    private final AISkillExtractionService aiSkillExtractionService;

    public AISkillExtractionController(
            AISkillExtractionService aiSkillExtractionService) {
        this.aiSkillExtractionService = aiSkillExtractionService;
    }

    @GetMapping("/extract-skills")
    public SkillExtractionResponse extractSkills(
            @RequestParam String bio) {

        return aiSkillExtractionService.extractSkills(bio);
    }
}