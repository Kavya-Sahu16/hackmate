package com.kavya.hackmate.team.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateTeamRequest {

    private String title;
    private String description;
    private String hackathonName;
    private String requiredSkills;
    private Integer teamSize;
    private String location;
}