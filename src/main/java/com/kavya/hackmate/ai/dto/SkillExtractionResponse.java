package com.kavya.hackmate.ai.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SkillExtractionResponse {

    private List<String> skills;
}