package com.tcs.ebms.dto;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;

public record PaymentDTO(
        @NotNull(message = "Bill ID cannot be null")
        Long billId,

        @NotNull(message = "Amount cannot be null")
        @Positive(message = "Amount must be positive")
        BigDecimal amount,

        @NotBlank(message = "Payment method cannot be blank")
        String paymentMethod
) {}