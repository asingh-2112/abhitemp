package com.tcs.ebms.config;

import com.tcs.ebms.model.Login;
import com.tcs.ebms.repository.LoginRepo;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class AdminInitializer {

    private final LoginRepo loginRepo;
    private final PasswordEncoder passwordEncoder;

    public AdminInitializer(LoginRepo loginRepo, PasswordEncoder passwordEncoder) {
        this.loginRepo = loginRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void initializeAdmin() {
        if (!loginRepo.existsByUsername("abc")) {
            Login admin = new Login();
            admin.setCid(1111111111111L);
            admin.setUsername("abhishek");
            admin.setEmail("abc@gmail.com");
            admin.setPassword(passwordEncoder.encode("1234"));
            admin.setStatus(true);
            admin.setRole("ADMIN");

            loginRepo.save(admin);
            System.out.println("Admin user created successfully!");
        }
    }
}