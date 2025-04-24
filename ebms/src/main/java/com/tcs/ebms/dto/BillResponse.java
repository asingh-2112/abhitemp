package com.tcs.ebms.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record BillResponse(
        Long id,
        Long customerId,
        String customerName,
        String billNumber,
        BigDecimal amount,
        LocalDate issueDate,
        LocalDate dueDate,
        String status,
        Long paymentId,
        String billingPeriod,
        String description
) {}