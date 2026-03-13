package com.devi.ai.ai_document_summariser.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
public class OpenAIRequest {

    private String model;
    private List<Message> messages;
    private double temperature;
    private int max_tokens;

    @Data
    @AllArgsConstructor
    public static class Message {
        private String role;
        private String content;
    }
}
