package com.kavya.hackmate.interest;

import com.kavya.hackmate.interest.dto.CreateInterestRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.kavya.hackmate.user.User;
import com.kavya.hackmate.user.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InterestService {

    private final InterestRepository interestRepository;
    private final UserRepository userRepository;
    

    public Interest createInterest(CreateInterestRequest request) {

        if (interestRepository.findByName(request.getName()).isPresent()) {
            throw new RuntimeException("Interest already exists");
        }

        Interest interest = Interest.builder()
                .name(request.getName())
                .build();

        return interestRepository.save(interest);
    }

    public void addInterestToUser(
            Long interestId,
            String username) {

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Interest interest = interestRepository.findById(interestId)
                .orElseThrow(() -> new RuntimeException("Interest not found"));

        user.getInterests().add(interest);

        userRepository.save(user);
    }

    public List<Interest> getMyInterests(String username) {

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return new ArrayList<>(user.getInterests());
    }
}


// Why? (FOR LINE 53 )
// Set  -> no duplicates
// List -> ordered collection
//Stored interests as a Set, but the API returns a List, so we convert it before returning.