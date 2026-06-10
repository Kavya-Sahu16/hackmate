package com.kavya.hackmate.user;

import com.kavya.hackmate.user.dto.UpdateProfileRequest;
import com.kavya.hackmate.user.dto.UserProfileResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserProfileResponse getMyProfile(String username) {

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return UserProfileResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .name(user.getName())
                .headline(user.getHeadline())
                .college(user.getCollege())
                .city(user.getCity())
                .graduationYear(user.getGraduationYear())
                .bio(user.getBio())
                .githubUrl(user.getGithubUrl())
                .linkedinUrl(user.getLinkedinUrl())
                .profilePictureUrl(user.getProfilePictureUrl())
                .build();
    }

    public UserProfileResponse updateMyProfile(
            String username,
            UpdateProfileRequest request) {

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setName(request.getName());
        user.setHeadline(request.getHeadline());
        user.setCollege(request.getCollege());
        user.setCity(request.getCity());
        user.setGraduationYear(request.getGraduationYear());
        user.setBio(request.getBio());
        user.setGithubUrl(request.getGithubUrl());
        user.setLinkedinUrl(request.getLinkedinUrl());
        user.setProfilePictureUrl(request.getProfilePictureUrl());

        userRepository.save(user);

        return UserProfileResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .name(user.getName())
                .headline(user.getHeadline())
                .college(user.getCollege())
                .city(user.getCity())
                .graduationYear(user.getGraduationYear())
                .bio(user.getBio())
                .githubUrl(user.getGithubUrl())
                .linkedinUrl(user.getLinkedinUrl())
                .profilePictureUrl(user.getProfilePictureUrl())
                .build();
    }
}