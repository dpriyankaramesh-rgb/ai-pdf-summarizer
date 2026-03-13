package com.devi.ai.ai_document_summariser.service;

import com.devi.ai.ai_document_summariser.entity.DocumentSummariserResponse;
import com.devi.ai.ai_document_summariser.utility.FileValidationUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class DocumentService {

    @Autowired
    private OpenAIService openAIService;

    @Autowired
    private PdfFileActionService pdfFileActionService;

    @Autowired
    private PdfGeneratorService pdfGeneratorService;

    FileValidationUtility fileValidationUtility = new FileValidationUtility();

    private String latestSummary = null;

    public DocumentSummariserResponse documentsAnalysisAndSummarisation (MultipartFile[] files) throws IOException {
        this.validateFiles(files);
        DocumentSummariserResponse documentSummariserResponse = this.summariseDocuments(files);
        latestSummary = documentSummariserResponse.getSummary().toString();
        return documentSummariserResponse;
    }

    public byte[] getDownloadSummarisedPdf(){
        return pdfGeneratorService.generatePdf(this.getLatestSummary());

    }


    private void validateFiles(MultipartFile[] files) {
        fileValidationUtility.validateFiles(files);
    }

    private DocumentSummariserResponse summariseDocuments(MultipartFile[] files) throws IOException {
        return openAIService.summariseDocuments(pdfFileActionService.extractPdf(files));
    }

    private String getLatestSummary() {
        if (latestSummary == null || latestSummary.isEmpty()) {
            throw new RuntimeException("No summary available to download");
        }
        return latestSummary;
    }
}
