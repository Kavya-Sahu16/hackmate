package com.kavya.hackmate.user;

import com.kavya.hackmate.user.dto.UpdateProfileRequest;
import com.kavya.hackmate.user.dto.UserProfileResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/me")
    public UserProfileResponse getMyProfile(Authentication authentication) {

        String username = authentication.getName();

        return userService.getMyProfile(username);
    }

    @PutMapping("/me")
    public UserProfileResponse updateMyProfile(
            Authentication authentication,
            @RequestBody UpdateProfileRequest request) {

        String username = authentication.getName();

        return userService.updateMyProfile(username, request);
    }
}