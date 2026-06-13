package com.kavya.hackmate.joinrequest.dto;

import com.kavya.hackmate.joinrequest.JoinRequestStatus;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JoinRequestResponse {

    private Long id;

    private String applicantUsername;

    private Long teamRequestId;

    private JoinRequestStatus status;
}