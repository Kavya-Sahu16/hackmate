package com.kavya.hackmate.joinrequest;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface JoinRequestRepository
        extends JpaRepository<JoinRequest, Long> {

    List<JoinRequest> findByTeamRequestId(Long teamRequestId);

    Optional<JoinRequest> findById(Long id);
}