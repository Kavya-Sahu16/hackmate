package com.kavya.hackmate.verification;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.kavya.hackmate.verification.dto.VerificationResponse;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
@RequestMapping("/api/verification")
@RequiredArgsConstructor
public class VerificationController {

    private final VerificationService verificationService;

    @PostMapping("/verify-student")
    public VerificationResponse verifyStudent(
            Authentication authentication) {

        String username = authentication.getName();

        return verificationService.verifyStudent(username);
    }
}
