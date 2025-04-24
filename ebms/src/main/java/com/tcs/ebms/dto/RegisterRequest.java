package com.tcs.ebms.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record RegisterRequest(

        @NotBlank(message = "User Id cannot be blank")
        @Size(min = 5, max = 50,  message = "User Id must be 5 to 50 characters")
        String username,

        @NotBlank(message = "Title cannot be empty.")
        String title,

        @NotBlank(message = "Name cannot be blank")
        @Size(max = 50,  message = "Name must be 5 to 50 characters")
        String name,

        @NotBlank(message = "Email cannot be blank")
        @Email(message = "Invalid email format")
        String email,


        @NotBlank(message = "Mobile number cannot be blank")
        @Pattern(
                regexp = "^[6-9]\\d{9}$",
                message = "Invalid phone number."
        )
        String mobileNumber,

        @NotBlank(message = "Bill number is required")
        @Pattern(regexp = "^\\d{5}$", message = "Bill number must be exactly 5 digits")
        String billNumber,

        @NotBlank(message = "Password cannot be blank")
        String password,

        @NotBlank(message = "Confirm password cannot be blank")
        String confirmPassword
) {


}