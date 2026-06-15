package com.kavya.hackmate.interest;

import com.kavya.hackmate.interest.dto.CreateInterestRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;
import java.util.List;

@RestController
@RequestMapping("/api/interests")
@RequiredArgsConstructor
public class InterestController {

    private final InterestService interestService;

    @PostMapping
    public Interest createInterest(
            @RequestBody CreateInterestRequest request) {

        return interestService.createInterest(request);
    }

    @PostMapping("/{interestId}/users")
    public void addInterestToUser(
            @PathVariable Long interestId,
            Authentication authentication) {

        interestService.addInterestToUser(
                interestId,
                authentication.getName());
    }

    @GetMapping("/me")
    public List<Interest> getMyInterests(
            Authentication authentication) {

        return interestService.getMyInterests(
                authentication.getName());
    }
}