package com.tcs.ebms.dto;

import java.time.LocalDateTime;

public record ComplaintResponse(
        Long complaintId,
        String complaintType,
        String category,
        String status,
        LocalDateTime createdAt,
        String customerName
) {}
