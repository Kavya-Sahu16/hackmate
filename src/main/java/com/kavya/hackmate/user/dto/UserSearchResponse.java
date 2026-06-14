package com.kavya.hackmate.user.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.Set;

@Getter
@Builder
public class UserSearchResponse {

    private Long id;

    private String username;

    private String name;

    private String headline;

    private String college;

    private String city;

    private Integer graduationYear;

    private Boolean verified;

    private Set<String> skills;
}