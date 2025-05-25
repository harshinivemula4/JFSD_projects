package com.example.day5javaapiclient;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashMap;
import java.util.Map;

public class SpeechResponse {
    private String text;

    @JsonProperty("speech_type")  // Map snake_case JSON field to camelCase Java field
    private String speechType;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getSpeechType() {
        return speechType;
    }

    public void setSpeechType(String speechType) {
        this.speechType = speechType;
    }

    public Map<String, String> toMap() {
        Map<String, String> map = new HashMap<>();
        map.put("text", text);
        map.put("speech_type", speechType);
        return map;
    }
}