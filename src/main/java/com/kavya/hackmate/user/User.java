package com.kavya.hackmate.user;

import com.kavya.hackmate.user.enums.Role;
import com.kavya.hackmate.user.enums.Status;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    private String headline;

    private String college;

    private String city;

    private Integer graduationYear;

    @Column(columnDefinition = "TEXT")
    private String bio;

    private String githubUrl;

    private String linkedinUrl;

    private String profilePictureUrl;

    private Boolean verified = false;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Enumerated(EnumType.STRING)
    private Status status;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
