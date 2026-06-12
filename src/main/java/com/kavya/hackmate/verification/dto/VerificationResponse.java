package com.kavya.hackmate.verification.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class VerificationResponse {

    private boolean verified;
    private String message;
}
