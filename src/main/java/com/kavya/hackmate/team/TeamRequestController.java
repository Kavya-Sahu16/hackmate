package com.kavya.hackmate.team;

import com.kavya.hackmate.team.dto.CreateTeamRequest;
import com.kavya.hackmate.team.dto.TeamRequestResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.DeleteMapping;  // athough line 7 includes it
import java.util.List;

@RestController
@RequestMapping("/api/team-requests")
@RequiredArgsConstructor
public class TeamRequestController {

    private final TeamRequestService teamRequestService;

    @PostMapping
    public TeamRequestResponse createTeamRequest(
            Authentication authentication,
            @RequestBody CreateTeamRequest request) {

        String username = authentication.getName();

        return teamRequestService.createTeamRequest(username, request);
    }

    @GetMapping
    public List<TeamRequestResponse> getAllTeamRequests() {

        return teamRequestService.getAllTeamRequests();
    }

    @GetMapping("/{id}")
    public TeamRequestResponse getTeamRequestById(
            @PathVariable Long id) {

        return teamRequestService.getTeamRequestById(id);
    }

    @DeleteMapping("/{id}")
    public String deleteTeamRequest(
            @PathVariable Long id,
            Authentication authentication) {

        String username = authentication.getName();

        teamRequestService.deleteTeamRequest(id, username);

        return "Team request deleted successfully";
    }
}