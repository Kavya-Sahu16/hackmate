package com.kavya.hackmate.user;

import com.kavya.hackmate.user.dto.UpdateProfileRequest;
import com.kavya.hackmate.user.dto.UserProfileResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.kavya.hackmate.skill.Skill;
import com.kavya.hackmate.skill.SkillRepository;
import com.kavya.hackmate.skill.dto.SkillResponse;
import com.kavya.hackmate.user.dto.UserSearchRequest;
import com.kavya.hackmate.user.specification.UserSpecification;
import com.kavya.hackmate.user.dto.UserSearchResponse;
import java.util.stream.Collectors;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

        private final UserRepository userRepository;
        private final SkillRepository skillRepository;

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
                                .verified(user.getVerified())
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

        public void addSkillToUser(String username, Long skillId) {

                User user = userRepository.findByUsername(username)
                                .orElseThrow(() -> new RuntimeException("User not found"));

                Skill skill = skillRepository.findById(skillId)
                                .orElseThrow(() -> new RuntimeException("Skill not found"));

                user.getSkills().add(skill);

                userRepository.save(user);
        }

        public void removeSkillFromUser(String username, Long skillId) {

                User user = userRepository.findByUsername(username)
                                .orElseThrow(() -> new RuntimeException("User not found"));

                Skill skill = skillRepository.findById(skillId)
                                .orElseThrow(() -> new RuntimeException("Skill not found"));

                user.getSkills().remove(skill);

                userRepository.save(user);
        }

        public List<SkillResponse> getMySkills(String username) {

                User user = userRepository.findByUsername(username)
                                .orElseThrow(() -> new RuntimeException("User not found"));

                return user.getSkills()
                                .stream()
                                .map(skill -> SkillResponse.builder()
                                                .id(skill.getId())
                                                .name(skill.getName())
                                                .category(skill.getCategory())
                                                .build())
                                .toList();
        }

        public List<UserSearchResponse> searchUsers(UserSearchRequest request) {

                return userRepository.findAll(
                                UserSpecification.searchUsers(request))
                                .stream()
                                .map(user -> UserSearchResponse.builder()
                                                .id(user.getId())
                                                .username(user.getUsername())
                                                .name(user.getName())
                                                .headline(user.getHeadline())
                                                .college(user.getCollege())
                                                .city(user.getCity())
                                                .graduationYear(user.getGraduationYear())
                                                .verified(user.getVerified())
                                                .skills(
                                                                user.getSkills()
                                                                                .stream()
                                                                                .map(skill -> skill.getName())
                                                                                .collect(Collectors.toSet()))
                                                .build())
                                .toList();
        }
}