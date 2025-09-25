package com.example.streaming;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class HealthController {
    @GetMapping("/") public String home() { return "OK - Streaming API no ar"; }
    @GetMapping("/ping") public String ping() { return "pong"; }
}