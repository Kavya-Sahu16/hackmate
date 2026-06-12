package com.kavya.hackmate.skill;

import com.kavya.hackmate.skill.dto.CreateSkillRequest;
import com.kavya.hackmate.skill.dto.SkillResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SkillService {

    private final SkillRepository skillRepository;

    public SkillResponse createSkill(CreateSkillRequest request){
        if (skillRepository.existsByName(request.getName())) {
        throw new RuntimeException("Skill already exists");
    }

    Skill skill = Skill.builder()
            .name(request.getName())
            .category(request.getCategory())
            .build();

    Skill savedSkill = skillRepository.save(skill);

    return SkillResponse.builder()
            .id(savedSkill.getId())
            .name(savedSkill.getName())
            .category(savedSkill.getCategory())
            .build();
    }

    public List<SkillResponse> getAllSkills(){
        return skillRepository.findAll()
            .stream()
            .map(skill -> SkillResponse.builder()
                    .id(skill.getId())
                    .name(skill.getName())
                    .category(skill.getCategory())
                    .build())
            .toList();
    }

    public SkillResponse getSkillById(Long id){
        Skill skill = skillRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Skill not found"));

    return SkillResponse.builder()
            .id(skill.getId())
            .name(skill.getName())
            .category(skill.getCategory())
            .build();
    }

}