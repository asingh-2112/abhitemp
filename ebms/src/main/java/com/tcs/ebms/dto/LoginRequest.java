package com.tcs.ebms.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record LoginRequest(
        @NotBlank(message = "User Id cannot be blank")
        @Size(min = 5, max = 50,  message = "User Id must be 5 to 50 characters")
        String username,

        @NotBlank(message = "Password cannot be blank")
        String password
) {
}
