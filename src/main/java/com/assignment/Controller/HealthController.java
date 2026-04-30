package com.assignment.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {

    @GetMapping("/hello")
    public String hello() {
        return "Backend Assignment Ready!";
    }

    @GetMapping("/health")
    public String health() {
        return "Application Running!";
    }
}
