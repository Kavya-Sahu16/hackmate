package com.kavya.hackmate.joinrequest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.kavya.hackmate.joinrequest.dto.JoinRequestResponse;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;

@RestController
@RequestMapping("/api/join-requests")
@RequiredArgsConstructor
public class JoinRequestController {

    private final JoinRequestService joinRequestService;

    @PostMapping("/team/{teamRequestId}")
    public JoinRequestResponse applyToTeamRequest(
            @PathVariable Long teamRequestId,
            Authentication authentication) {

        String username = authentication.getName();

        return joinRequestService.applyToTeamRequest(
                teamRequestId,
                username);
    }

    @GetMapping("/team/{teamRequestId}")
    public List<JoinRequestResponse> getRequestsForTeam(
            @PathVariable Long teamRequestId) {

        return joinRequestService.getRequestsForTeam(teamRequestId);
    }
}