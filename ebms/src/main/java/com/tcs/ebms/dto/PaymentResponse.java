package com.tcs.ebms.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record PaymentResponse(
        Long id,
        Long billId,
        BigDecimal amount,
        LocalDateTime paymentDate,
        String paymentMethod,
        String transactionId,
        String status
) {}