package com.kavya.hackmate.user.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserSearchRequest {

    private String skill;

    private String city;

    private String college;

    private Integer graduationYear;
}