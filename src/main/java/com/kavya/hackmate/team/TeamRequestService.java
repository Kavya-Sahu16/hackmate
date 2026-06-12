package com.kavya.hackmate.team;

import com.kavya.hackmate.team.dto.CreateTeamRequest;
import com.kavya.hackmate.team.dto.TeamRequestResponse;
import com.kavya.hackmate.user.User;
import com.kavya.hackmate.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TeamRequestService {

    private final TeamRequestRepository teamRequestRepository;
    private final UserRepository userRepository;

    public TeamRequestResponse createTeamRequest(
            String username,
            CreateTeamRequest request) {

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        TeamRequest teamRequest = TeamRequest.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .hackathonName(request.getHackathonName())
                .requiredSkills(request.getRequiredSkills())
                .teamSize(request.getTeamSize())
                .location(request.getLocation())
                .createdAt(LocalDateTime.now())
                .createdBy(user)
                .build();

        TeamRequest saved = teamRequestRepository.save(teamRequest);

        return TeamRequestResponse.builder()
                .id(saved.getId())
                .title(saved.getTitle())
                .description(saved.getDescription())
                .hackathonName(saved.getHackathonName())
                .requiredSkills(saved.getRequiredSkills())
                .teamSize(saved.getTeamSize())
                .location(saved.getLocation())
                .createdBy(saved.getCreatedBy().getUsername())
                .build();
    }

    // Get All Team Requests
    public List<TeamRequestResponse> getAllTeamRequests() {

        return teamRequestRepository.findAll()
                .stream()
                .map(teamRequest -> TeamRequestResponse.builder()
                        .id(teamRequest.getId())
                        .title(teamRequest.getTitle())
                        .description(teamRequest.getDescription())
                        .hackathonName(teamRequest.getHackathonName())
                        .requiredSkills(teamRequest.getRequiredSkills())
                        .teamSize(teamRequest.getTeamSize())
                        .location(teamRequest.getLocation())
                        .createdBy(teamRequest.getCreatedBy().getUsername())
                        .build())
                .toList();
    }

    // Get Team Request By Id
    public TeamRequestResponse getTeamRequestById(Long id) {

        TeamRequest teamRequest = teamRequestRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Team request not found"));

        return TeamRequestResponse.builder()
                .id(teamRequest.getId())
                .title(teamRequest.getTitle())
                .description(teamRequest.getDescription())
                .hackathonName(teamRequest.getHackathonName())
                .requiredSkills(teamRequest.getRequiredSkills())
                .teamSize(teamRequest.getTeamSize())
                .location(teamRequest.getLocation())
                .createdBy(teamRequest.getCreatedBy().getUsername())
                .build();
    }

    public void deleteTeamRequest(Long id, String username) {

        TeamRequest teamRequest = teamRequestRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Team request not found"));

        if (!teamRequest.getCreatedBy().getUsername().equals(username)) {
            throw new RuntimeException("You can only delete your own team requests");
        }

        teamRequestRepository.delete(teamRequest);
    }
}