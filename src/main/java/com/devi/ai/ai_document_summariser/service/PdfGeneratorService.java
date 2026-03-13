package com.devi.ai.ai_document_summariser.service;

import com.lowagie.text.Document;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.stereotype.Service;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;

import java.io.ByteArrayOutputStream;
import java.util.Map;

@Service
public class PdfGeneratorService {

    public byte[] generatePdf(String summary) {

        try {

            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootNode = mapper.readTree(summary);

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            Document document = new Document();
            PdfWriter.getInstance(document, outputStream);

            document.open();

            document.add(new Paragraph("AI Document Summary"));
            document.add(new Paragraph(" "));

            writeJsonNode(document, rootNode, 0);
            document.close();
            return outputStream.toByteArray();

        } catch (Exception e) {
            throw new RuntimeException("Error generating PDF");
        }
    }

    private void writeJsonNode(Document document, JsonNode node, int indent) throws Exception {
        String indentation = " ".repeat(indent * 4);
        if (node.isObject()) {
            for (Map.Entry<String, JsonNode> entry : node.properties()) {
                document.add(new Paragraph(indentation + entry.getKey() + ":"));
                writeJsonNode(document, entry.getValue(), indent + 1);
            }
        } else if (node.isArray()) {
            for (JsonNode item : node) {
                writeJsonNode(document, item, indent + 1);
            }
        } else {
            document.add(new Paragraph(indentation + "- " + node.asText()));
        }
    }
}
