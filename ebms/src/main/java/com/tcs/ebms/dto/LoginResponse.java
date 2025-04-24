package com.tcs.ebms.dto;

public record LoginResponse(
        String username,
        String role,
        Long cid
) {
}