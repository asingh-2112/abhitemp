package com.tcs.ebms.dto;

import jakarta.validation.constraints.*;

public record ComplaintRequest(
        @NotBlank String complaintType,
        @NotBlank String category,
        @NotBlank String landMark,
        @NotBlank String customerName,
        @NotBlank @Size(min = 10, max = 500) String problem,
        @NotNull @Positive Long costumerId,
        @NotBlank String address,
        @NotBlank @Pattern(regexp = "^[6-9]\\d{9}$") String mobileNumber
) {}