package com.tcs.ebms.dto;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;

public record BillDTO(
        @NotNull(message = "Customer ID cannot be null")
        Long customerId,

        @NotNull(message = "Amount cannot be null")
        @Positive(message = "Amount must be positive")
        BigDecimal amount,

        @NotNull(message = "Issue date cannot be null")
        LocalDate issueDate,

        @NotNull(message = "Due date cannot be null")
        LocalDate dueDate,

        @NotBlank(message = "Billing period cannot be blank")
        String billingPeriod,

        String description
) {}