package com.tcs.ebms.repository;

import com.tcs.ebms.model.Login;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LoginRepo extends JpaRepository<Login, Long> {
    Optional<Login> findByUsername(String username);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);

    Optional<Login> findByUsernameAndRole(@NotBlank(message = "User Id cannot be blank") @Size(min = 5, max = 50,  message = "User Id must be 5 to 50 characters") String username, String admin);
}