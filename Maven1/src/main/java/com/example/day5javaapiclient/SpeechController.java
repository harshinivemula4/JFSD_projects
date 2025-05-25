package com.example.day5javaapiclient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.Map;

@RestController
@RequestMapping("/speech")
public class SpeechController {

    @Autowired
    private SpeechService speechService;

    @PostMapping("/analyze")
    public Mono<Map<String, String>> analyzeSpeech(@RequestBody Map<String, String> request) {
        String text = request.get("text");
        return speechService.analyzeSpeech(text);
    }
}