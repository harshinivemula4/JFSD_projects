package com.example.day5javaapiclient;

import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@Service
public class VisionService {

    @Autowired
    private VisionClient visionClient;

    @Async
    @Retry(name = "apiRetry")
    public CompletableFuture<Map<String, String>> predictVision(String imageId) {
        try {
            Map<String, String> body = new HashMap<>();
            body.put("image_id", imageId);
            Map<String, String> response = visionClient.predictVision(body);
            return CompletableFuture.completedFuture(response);
        } catch (Exception e) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "Failed to predict vision: " + e.getMessage());
            return CompletableFuture.completedFuture(errorResponse);
        }
    }
}