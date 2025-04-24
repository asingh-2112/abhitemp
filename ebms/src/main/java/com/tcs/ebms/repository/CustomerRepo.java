package com.tcs.ebms.repository;

import com.tcs.ebms.model.Customer;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CustomerRepo extends JpaRepository<Customer, Long> {

    boolean existsByEmail(@NotBlank(message = "Email cannot be blank") @Email(message = "Invalid email format") String email);

    Optional<Customer> findByUsername(@NotBlank(message = "User Id cannot be blank") @Size(min = 5, max = 50,  message = "User Id must be 5 to 50 characters") String username);

    boolean existsByUsername(@NotBlank(message = "User Id cannot be blank") @Size(min = 5, max = 50,  message = "User Id must be 5 to 50 characters") String username);
}