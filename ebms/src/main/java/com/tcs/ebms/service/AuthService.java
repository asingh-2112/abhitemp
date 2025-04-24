package com.tcs.ebms.service;

import com.tcs.ebms.dto.LoginResponse;
import com.tcs.ebms.dto.LoginRequest;
import com.tcs.ebms.dto.RegisterRequest;
import com.tcs.ebms.dto.RegisterResponse;
import com.tcs.ebms.model.Customer;
import com.tcs.ebms.model.Login;
import com.tcs.ebms.repository.LoginRepo;
import com.tcs.ebms.repository.CustomerRepo;
import com.tcs.ebms.util.CustomerIdGenerator;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AuthService {
    private final CustomerRepo customerRepo;
    private final LoginRepo loginRepo;
    private final PasswordEncoder passwordEncoder;
    private final CustomerIdGenerator customerIdGenerator;

//    Added @Transactional to ensure both saves happen atomically
    @Transactional
    public RegisterResponse registerCustomer(RegisterRequest registerRequest) {
        // Check if username or email already exists in either table
        if (customerRepo.existsByEmail(registerRequest.email())){
            throw new IllegalArgumentException("Email already exists");
        }
        if (loginRepo.existsByEmail(registerRequest.email())) {
            throw new IllegalArgumentException("Email already exists");
        }
        if (customerRepo.existsByUsername(registerRequest.username())) {
            throw new IllegalArgumentException("Username already exists");
        }
        if (loginRepo.existsByUsername(registerRequest.username())) {
            throw new IllegalArgumentException("Username already exists");
        }
        if (!registerRequest.password().equals(registerRequest.confirmPassword())) {
            throw new IllegalArgumentException("Passwords do not match");
        }

        // Create and save User
        Customer customer = new Customer();
        customer.setCid(customerIdGenerator.generate());
        customer.setUsername(registerRequest.username());
        customer.setTitle(registerRequest.title());
        customer.setName(registerRequest.name());
        customer.setEmail(registerRequest.email());
        customer.setMobileNumber(registerRequest.mobileNumber());
        customer.setBillNumber(registerRequest.billNumber());
        customer.setPassword(passwordEncoder.encode(registerRequest.password()));
        customer.setRole("CUSTOMER");
        customer.setActive(true);

        Customer savedUser = customerRepo.save(customer);


        // Create and save Login
        Login login = new Login();
        login.setCid(savedUser.getCid());
        login.setUsername(savedUser.getUsername());
        login.setEmail(savedUser.getEmail());
        login.setPassword(savedUser.getPassword());
        login.setStatus(savedUser.isActive());
        login.setRole(savedUser.getRole());
        Login l=loginRepo.save(login);

        return new RegisterResponse(savedUser.getCid(), savedUser.getName());
    }

    public LoginResponse loginCustomer(@Valid LoginRequest loginRequest) {
        Optional<Login> loginOptional = loginRepo.findByUsernameAndRole(loginRequest.username(),"CUSTOMER");

        if (loginOptional.isEmpty()) {
            throw new IllegalArgumentException("User not found");
        }

        Login login = loginOptional.get();

        if (!login.isStatus()) {
            throw new IllegalArgumentException("User account is inactive");
        }

        if (!passwordEncoder.matches(loginRequest.password(), login.getPassword())) {
            throw new IllegalArgumentException("Invalid credentials");
        }


        return new LoginResponse(login.getUsername(), "customer", login.getCid());
    }

    public LoginResponse loginAdmin(@Valid LoginRequest loginRequest) {


        Optional<Login> loginOptional=loginRepo.findByUsernameAndRole(loginRequest.username(),"ADMIN");

        if (loginOptional.isEmpty()) {
            throw new IllegalArgumentException("User not found");
        }
        Login login = loginOptional.get();

        if (!login.isStatus()) {
            throw new IllegalArgumentException("User account is inactive");
        }
        if (!passwordEncoder.matches(loginRequest.password(), login.getPassword())) {
            throw new IllegalArgumentException("Invalid credentials");
        }

        return new LoginResponse(login.getUsername(), "ADMIN", login.getCid());
    }
}