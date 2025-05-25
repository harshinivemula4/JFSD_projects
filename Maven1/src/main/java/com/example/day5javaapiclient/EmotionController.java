package com.example.day5javaapiclient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;  // Add this import

import java.util.Map;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/emotion")
public class EmotionController {

    @Autowired
    private EmotionService emotionService;

    @PostMapping("/classify")
    public CompletableFuture<Map<String, String>> classifyEmotion(@RequestBody Map<String, String> request) {
        String text = request.get("text");
        return emotionService.classifyEmotion(text);
    }
}
