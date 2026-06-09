package com.kavya.hackmate.user;


import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByEmail(String email); // if email already exists return true

    boolean existsByUsername(String username);  //if username already exists return true (Used during registration)

    Optional<User> findByEmail(String email);   // returns Optional<User> (Used during login)

    Optional<User> findByUsername(String username);
}