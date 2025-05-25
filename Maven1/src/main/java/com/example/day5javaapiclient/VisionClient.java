package com.example.day5javaapiclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

@FeignClient(name = "vision-api", url = "http://localhost:8001")
public interface VisionClient {

    @PostMapping("/predict/vision")
    Map<String, String> predictVision(@RequestBody Map<String, String> request);
}