package com.kavya.hackmate.joinrequest;

import com.kavya.hackmate.team.TeamRequest;
import com.kavya.hackmate.user.User;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "join_requests")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JoinRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "applicant_id")
    private User applicant;

    @ManyToOne
    @JoinColumn(name = "team_request_id")
    private TeamRequest teamRequest;

    @Enumerated(EnumType.STRING)
    private JoinRequestStatus status;
}