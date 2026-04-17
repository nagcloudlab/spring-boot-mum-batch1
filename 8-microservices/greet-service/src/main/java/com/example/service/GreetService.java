package com.example.service;

import java.time.LocalTime;
import java.time.format.DateTimeParseException;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.dto.GreetResponse;

@Service
public class GreetService {

    public GreetResponse greet(String time) {
        final int hour;
        if (time == null || time.isBlank()) {
            hour = LocalTime.now().getHour();
        } else {
            try {
                hour = LocalTime.parse(time).getHour();
            } catch (DateTimeParseException ex) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "time must be in HH:mm:ss format");
            }
        }

        final String greeting;
        if (hour >= 5 && hour < 12) {
            greeting = "Good morning";
        } else if (hour >= 12 && hour < 17) {
            greeting = "Good afternoon";
        } else if (hour >= 17 && hour < 21) {
            greeting = "Good evening";
        } else {
            greeting = "Good night";
        }

        return new GreetResponse(greeting);
    }
}
