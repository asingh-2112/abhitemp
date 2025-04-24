package com.tcs.ebms.util;

import org.springframework.stereotype.Component;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class BillNumberGenerator {
    private final AtomicInteger dailySequence = new AtomicInteger(1);
    private String currentDate;

    public synchronized String generate() {
        String today = LocalDate.now().format(DateTimeFormatter.BASIC_ISO_DATE);

        if (!today.equals(currentDate)) {
            dailySequence.set(1); // Reset sequence daily
            currentDate = today;
        }

        return "EBMS-" + currentDate + "-" +
                String.format("%03d", dailySequence.getAndIncrement());
    }
}