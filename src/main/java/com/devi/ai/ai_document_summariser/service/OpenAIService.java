package com.devi.ai.ai_document_summariser.service;

import com.devi.ai.ai_document_summariser.entity.DocumentSummariserResponse;
import com.devi.ai.ai_document_summariser.entity.OpenAIRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;

import java.util.List;

@Service
public class OpenAIService {

    @Value("${openai.api.key}")
    private String apiKey;

    @Value("${openai.url}")
    private String apiUrl;

    private final RestTemplate restTemplate = new RestTemplate();

    public DocumentSummariserResponse summariseDocuments(List<String> fileContents) {

        System.out.println("Entered OpenAiServices");

        List<OpenAIRequest.Message> messages = List.of(
                new OpenAIRequest.Message("system", "You are an expert document analyzer. Analyse the documents and summarise the content in the documents. Return the response in a VALID JSON only\n" +
                        "Do not include markdown.\n" +
                        "Do not include explanation.\n" +
                        "Return JSON only"),
                new OpenAIRequest.Message("user", "Analyze the documents given and provide the summary of the documents : " + fileContents)
        );
        
        OpenAIRequest openAIRequest = new OpenAIRequest();
        openAIRequest.setModel("gpt-4o-mini");
        openAIRequest.setMessages(messages);
        openAIRequest.setTemperature(0.3);
        openAIRequest.setMax_tokens(5000);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(apiKey);

        HttpEntity<OpenAIRequest> entity = new HttpEntity<>(openAIRequest, headers);
        ResponseEntity<String> response = restTemplate.postForEntity(apiUrl, entity, String.class);

        ObjectMapper mapper = new ObjectMapper();

        JsonNode root = mapper.readTree(response.getBody());

        String content = root
                .path("choices")
                .get(0)
                .path("message")
                .path("content")
                .asText();

        JsonNode summaryJson;

        try {
            summaryJson = mapper.readTree(content);
        } catch (Exception e) {
            System.out.println("Invalid JSON from OpenAI: " + content);

            summaryJson = mapper.createObjectNode()
                    .put("error", "AI response was not valid JSON")
                    .put("raw_response", content);
        }

        DocumentSummariserResponse responseObj = new DocumentSummariserResponse();
        responseObj.setSummary(summaryJson);

        return responseObj;

    }
}
