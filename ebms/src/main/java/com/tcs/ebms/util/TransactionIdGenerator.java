package com.tcs.ebms.util;

import org.springframework.stereotype.Component;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Component
public class TransactionIdGenerator {
    public String generate() {
        return "TXN-" +
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")) +
                "-" + UUID.randomUUID().toString().substring(0, 4).toUpperCase();
    }
}