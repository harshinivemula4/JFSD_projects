package com.example.day5javaapiclient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/vision")
public class VisionController {

    @Autowired
    private VisionService visionService;

    @PostMapping("/predict")
    public CompletableFuture<Map<String, String>> predictVision(@RequestBody Map<String, String> request) {
        String imageId = request.get("image_id");
        return visionService.predictVision(imageId);
    }
}