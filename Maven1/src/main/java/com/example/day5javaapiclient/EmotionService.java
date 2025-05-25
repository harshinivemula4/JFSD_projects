package com.example.day5javaapiclient;

import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@Service
public class EmotionService {

    @Autowired
    private RestTemplate restTemplate;

    @Async
    @Retry(name = "apiRetry")
    public CompletableFuture<Map<String, String>> classifyEmotion(String text) {
        try {
            String url = "http://localhost:8001/classify/emotion";
            HttpHeaders headers = new HttpHeaders();
            headers.set("Content-Type", "application/json");

            Map<String, String> body = new HashMap<>();
            body.put("text", text);

            HttpEntity<Map<String, String>> entity = new HttpEntity<>(body, headers);

            ResponseEntity<Map> response = restTemplate.exchange(url, HttpMethod.POST, entity, Map.class);
            return CompletableFuture.completedFuture(response.getBody());
        } catch (Exception e) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "Failed to classify emotion: " + e.getMessage());
            return CompletableFuture.completedFuture(errorResponse);
        }
    }
}