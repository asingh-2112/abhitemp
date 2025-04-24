package com.tcs.ebms.util;

import org.springframework.stereotype.Component;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

@Component
public class CustomerIdGenerator {
    private final Random random = new Random();
    private final AtomicLong lastTimestamp = new AtomicLong(0);

    public synchronized Long generate() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyMMddHHmmss");
        String timestampPart = now.format(formatter);

        // Prevent duplicates within same second
        long currentTimestamp = Long.parseLong(timestampPart);
        while (currentTimestamp <= lastTimestamp.get()) {
            currentTimestamp++;
        }
        lastTimestamp.set(currentTimestamp);

        return Long.parseLong(currentTimestamp + "" + random.nextInt(10));
    }
}