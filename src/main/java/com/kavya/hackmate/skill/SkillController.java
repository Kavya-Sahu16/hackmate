package com.kavya.hackmate.skill;

import com.kavya.hackmate.skill.dto.CreateSkillRequest;
import com.kavya.hackmate.skill.dto.SkillResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/skills")
@RequiredArgsConstructor
public class SkillController {

    private final SkillService skillService;

    @PostMapping
    public SkillResponse createSkill(
            @RequestBody CreateSkillRequest request) {

        return skillService.createSkill(request);
    }

    @GetMapping
    public List<SkillResponse> getAllSkills() {

        return skillService.getAllSkills();
    }

    @GetMapping("/{id}")
    public SkillResponse getSkillById(
            @PathVariable Long id) {

        return skillService.getSkillById(id);
    }
}