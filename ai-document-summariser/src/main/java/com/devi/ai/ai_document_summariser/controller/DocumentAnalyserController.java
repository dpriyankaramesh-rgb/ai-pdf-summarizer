package com.devi.ai.ai_document_summariser.controller;

import com.devi.ai.ai_document_summariser.entity.DocumentSummariserResponse;
import com.devi.ai.ai_document_summariser.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/document")
public class DocumentAnalyserController {

    @Autowired
    private DocumentService documentService;



    @PostMapping("/summarizer")
    public DocumentSummariserResponse analyze(@RequestParam(value = "files", required = false) MultipartFile[] files) throws IOException {

        System.out.println("Entered Controller");

        return documentService.documentsAnalysisAndSummarisation(files);
    }

    @GetMapping("/download")
    public ResponseEntity<byte[]> downloadSummary() {

        byte[] pdf = documentService.getDownloadSummarisedPdf();

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=summary.pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdf);
    }


}