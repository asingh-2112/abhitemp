package com.tcs.ebms.controllers;

import com.tcs.ebms.dto.LoginResponse;
import com.tcs.ebms.dto.LoginRequest;
import com.tcs.ebms.dto.RegisterRequest;
import com.tcs.ebms.dto.RegisterResponse;
import com.tcs.ebms.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> register(@Valid @RequestBody RegisterRequest registerRequest) {
        RegisterResponse response = authService.registerCustomer(registerRequest);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> customerLogin(@Valid @RequestBody LoginRequest loginRequest) {
        System.out.println("hello");
        LoginResponse response = authService.loginCustomer(loginRequest);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/adminlogin")
    public ResponseEntity<LoginResponse> adminLogin(@Valid @RequestBody LoginRequest loginRequest) {
        LoginResponse response = authService.loginAdmin(loginRequest);
        return ResponseEntity.ok(response);
    }
}