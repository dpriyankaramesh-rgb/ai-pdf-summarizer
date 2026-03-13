package com.devi.ai.ai_document_summariser.entity;

import lombok.Data;
import tools.jackson.databind.JsonNode;

@Data
public class DocumentSummariserResponse {

    private JsonNode summary;

}