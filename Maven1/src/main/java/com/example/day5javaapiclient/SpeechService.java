package com.example.day5javaapiclient;

import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

@Service
public class SpeechService {

    private final WebClient webClient;

    public SpeechService() {
        this.webClient = WebClient.builder().baseUrl("http://localhost:8001").build();
    }

    @Retry(name = "apiRetry")
    public Mono<Map<String, String>> analyzeSpeech(String text) {
        Map<String, String> body = new HashMap<>();
        body.put("text", text);

        return webClient.post()
                .uri("/analyze/speech")
                .bodyValue(body)
                .retrieve()
                .bodyToMono(SpeechResponse.class)
                .map(SpeechResponse::toMap)
                .onErrorResume(Throwable.class, e -> {
                    Map<String, String> errorResponse = new HashMap<>();
                    errorResponse.put("error", "Failed to analyze speech: " + e.getMessage());
                    return Mono.just(errorResponse);
                });
    }
}