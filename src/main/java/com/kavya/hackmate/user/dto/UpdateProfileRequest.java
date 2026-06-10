package com.kavya.hackmate.user.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateProfileRequest {

    private String name;
    private String headline;
    private String college;
    private String city;
    private Integer graduationYear;
    private String bio;
    private String githubUrl;
    private String linkedinUrl;
    private String profilePictureUrl;
}