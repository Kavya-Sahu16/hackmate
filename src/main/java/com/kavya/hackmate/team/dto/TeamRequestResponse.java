package com.kavya.hackmate.team.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TeamRequestResponse {

    private Long id;
    private String title;
    private String description;
    private String hackathonName;
    private String requiredSkills;
    private Integer teamSize;
    private String location;
    private String createdBy;
}