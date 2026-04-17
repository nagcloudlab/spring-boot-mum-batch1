package com.example.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.GreetResponse;
import com.example.service.GreetService;

@RestController
public class GreetController {

    private final GreetService greetService;

    public GreetController(GreetService greetService) {
        this.greetService = greetService;
    }

    // time : HH:mm:ss
    // GET /greet?time=09:30:00
    @GetMapping("/greet")
    public GreetResponse greet(@RequestParam(required = false) String time) {
        return greetService.greet(time);
    }
}
