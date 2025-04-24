package com.tcs.ebms.dto;

public record CustomerResponse(
        Long customerId,
        String username,
        String name,
        String email,
        String mobileNumber,
        boolean isActive
) {}