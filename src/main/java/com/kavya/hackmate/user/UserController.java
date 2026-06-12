package com.kavya.hackmate.user;

import com.kavya.hackmate.user.dto.UpdateProfileRequest;
import com.kavya.hackmate.user.dto.UserProfileResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import com.kavya.hackmate.skill.dto.AddSkillRequest;
import com.kavya.hackmate.skill.dto.SkillResponse;

import java.util.List;

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

    @PostMapping("/me/skills")
    public void addSkillToUser(
            Authentication authentication,
            @RequestBody AddSkillRequest request) {

        String username = authentication.getName();

        userService.addSkillToUser(username, request.getSkillId());
    }

    @GetMapping("/me/skills")
    public List<SkillResponse> getMySkills(
            Authentication authentication) {

        String username = authentication.getName();

        return userService.getMySkills(username);
    }

    @DeleteMapping("/me/skills/{skillId}")
    public void removeSkillFromUser(
            Authentication authentication,
            @PathVariable Long skillId) {

        String username = authentication.getName();

        userService.removeSkillFromUser(username, skillId);
    }
}