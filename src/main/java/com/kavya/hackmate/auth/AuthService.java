package com.kavya.hackmate.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.kavya.hackmate.user.UserRepository;
import com.kavya.hackmate.auth.dto.RegisterRequest;
import com.kavya.hackmate.exception.BadRequestException;
import com.kavya.hackmate.user.User;
import com.kavya.hackmate.user.enums.Role;
import com.kavya.hackmate.user.enums.Status;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.kavya.hackmate.auth.dto.LoginRequest;
import com.kavya.hackmate.auth.dto.AuthResponse;
import com.kavya.hackmate.security.JwtService;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor

public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public void register(RegisterRequest request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new BadRequestException("Email already registered");
        }

        if (userRepository.existsByUsername(request.getUsername())) {
            throw new BadRequestException("Username already taken");
        }

        User user = User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .status(Status.ACTIVE)
                .verified(false)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        userRepository.save(user);
    }

    public AuthResponse login(LoginRequest request) {

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new BadRequestException("Invalid username or password"));

        if (!passwordEncoder.matches(
                request.getPassword(),
                user.getPassword())) {

            throw new BadRequestException("Invalid username or password");
        }

        String token = jwtService.generateToken(user.getUsername());

        return AuthResponse.builder()
                .token(token)
                .build();
    }
}

/*
 * what the register method does-
 * 1. Check email uniqueness
 * 2. Check username uniqueness
 * 3. Create User object
 * 4. Set default values
 * 5. Save user to database
 */