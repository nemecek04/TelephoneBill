package com.phonecompany.billing.dto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TelephoneRecord {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
    private final Long phone;
    private final LocalDateTime start;
    private final LocalDateTime end;

    private Long price = 0L;

    public TelephoneRecord(String row) {
        String[] record = row.split(",");
        phone = Long.parseLong(record[0]);
        start = LocalDateTime.parse(record[1], formatter);
        end = LocalDateTime.parse(record[2], formatter);

        LocalDateTime s = start;
        int i = 0;

        while (s.isBefore(end)) {
            if (i < 5) {
                if (s.getHour() >= 8 && s.getHour() < 16) {
                    price += 100;
                } else {
                    price += 50;
                }
            } else {
                price += 20;
            }

            s = s.plusMinutes(1L);
            i++;
        }
    }

    public Long getPhone() {
        return phone;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public Long getPrice() {
        return price;
    }
}
