package com.kavya.hackmate.verification;

import com.kavya.hackmate.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.kavya.hackmate.user.User;
import com.kavya.hackmate.verification.dto.VerificationResponse;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class VerificationService {

    private final UserRepository userRepository;

    private static final Set<String> APPROVED_DOMAINS = Set.of(
            "iitb.ac.in",
            "iitd.ac.in",
            "iiitd.ac.in",
            "nitk.edu.in");

    public VerificationResponse verifyStudent(String username) {

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        String email = user.getEmail();

        String domain = email.substring(email.indexOf("@") + 1);

        if (APPROVED_DOMAINS.contains(domain)) {

            user.setVerified(true);

            userRepository.save(user);

            return VerificationResponse.builder()
                    .verified(true)
                    .message("Student verified successfully")
                    .build();
        }

        return VerificationResponse.builder()
                .verified(false)
                .message("College domain not approved")
                .build();
    }
}