package com.kavya.hackmate.joinrequest;

import com.kavya.hackmate.joinrequest.dto.JoinRequestResponse;
import com.kavya.hackmate.notification.NotificationService;
import com.kavya.hackmate.notification.enums.NotificationType;
import com.kavya.hackmate.team.TeamRequest;
import com.kavya.hackmate.team.TeamRequestRepository;
import com.kavya.hackmate.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.kavya.hackmate.user.User;
import java.util.List;

@Service
@RequiredArgsConstructor
public class JoinRequestService {

        private final JoinRequestRepository joinRequestRepository;
        private final UserRepository userRepository;
        private final TeamRequestRepository teamRequestRepository;
        private final NotificationService notificationService;

        public JoinRequestResponse applyToTeamRequest(
                        Long teamRequestId,
                        String username) {

                User applicant = userRepository.findByUsername(username)
                                .orElseThrow(() -> new RuntimeException("User not found"));

                TeamRequest teamRequest = teamRequestRepository.findById(teamRequestId)
                                .orElseThrow(() -> new RuntimeException("Team request not found"));

                JoinRequest joinRequest = JoinRequest.builder()
                                .applicant(applicant)
                                .teamRequest(teamRequest)
                                .status(JoinRequestStatus.PENDING)
                                .build();

                JoinRequest saved = joinRequestRepository.save(joinRequest);
                notificationService.createNotification(
                                teamRequest.getCreatedBy(),
                                applicant.getUsername() +
                                                " applied to your team: " +
                                                teamRequest.getTitle(),
                                NotificationType.NEW_JOIN_REQUEST);

                return JoinRequestResponse.builder()
                                .id(saved.getId())
                                .applicantUsername(saved.getApplicant().getUsername())
                                .teamRequestId(saved.getTeamRequest().getId())
                                .status(saved.getStatus())
                                .build();
        }

        public List<JoinRequestResponse> getRequestsForTeam(Long teamRequestId) {

                List<JoinRequest> joinRequests = joinRequestRepository.findByTeamRequestId(teamRequestId);

                return joinRequests.stream()
                                .map(joinRequest -> JoinRequestResponse.builder()
                                                .id(joinRequest.getId())
                                                .applicantUsername(joinRequest.getApplicant().getUsername())
                                                .teamRequestId(joinRequest.getTeamRequest().getId())
                                                .status(joinRequest.getStatus())
                                                .build())
                                .toList();
        }

        public JoinRequestResponse acceptRequest(
                        Long requestId,
                        String username) {

                JoinRequest joinRequest = joinRequestRepository.findById(requestId)
                                .orElseThrow(() -> new RuntimeException("Join request not found"));

                TeamRequest teamRequest = joinRequest.getTeamRequest();

                if (!teamRequest.getCreatedBy().getUsername().equals(username)) {
                        throw new RuntimeException("You are not allowed to accept this request");
                }

                joinRequest.setStatus(JoinRequestStatus.ACCEPTED);

                JoinRequest saved = joinRequestRepository.save(joinRequest);

                notificationService.createNotification(
                                joinRequest.getApplicant(),
                                "Your join request for '" +
                                                teamRequest.getTitle() +
                                                "' was accepted.",
                                NotificationType.NEW_JOIN_REQUEST);

                return JoinRequestResponse.builder()
                                .id(saved.getId())
                                .applicantUsername(saved.getApplicant().getUsername())
                                .teamRequestId(saved.getTeamRequest().getId())
                                .status(saved.getStatus())
                                .build();
        }

        public JoinRequestResponse rejectRequest(
                        Long requestId,
                        String username) {

                JoinRequest joinRequest = joinRequestRepository.findById(requestId)
                                .orElseThrow(() -> new RuntimeException("Join request not found"));

                TeamRequest teamRequest = joinRequest.getTeamRequest();

                if (!teamRequest.getCreatedBy().getUsername().equals(username)) {
                        throw new RuntimeException("You are not allowed to reject this request");
                }

                joinRequest.setStatus(JoinRequestStatus.REJECTED);

                JoinRequest saved = joinRequestRepository.save(joinRequest);

                notificationService.createNotification(
                                joinRequest.getApplicant(),
                                "Your join request for '" +
                                                teamRequest.getTitle() +
                                                "' was rejected.",
                                NotificationType.NEW_JOIN_REQUEST);

                return JoinRequestResponse.builder()
                                .id(saved.getId())
                                .applicantUsername(saved.getApplicant().getUsername())
                                .teamRequestId(saved.getTeamRequest().getId())
                                .status(saved.getStatus())
                                .build();
        }
}