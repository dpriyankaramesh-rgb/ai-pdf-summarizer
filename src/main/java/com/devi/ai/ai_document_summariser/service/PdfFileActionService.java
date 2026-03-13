package com.devi.ai.ai_document_summariser.service;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class PdfFileActionService {

    public List<String> extractPdf (MultipartFile[] files) throws IOException {

        List<String> fileContentList = new ArrayList<>();
        PDFTextStripper pdfTextStripper = new PDFTextStripper();

        for (MultipartFile file : files){
            PDDocument pdDocument = PDDocument.load(file.getInputStream());
            String filecontent = pdfTextStripper.getText(pdDocument);
            fileContentList.add(filecontent);
            pdDocument.close();
        }
        return fileContentList;
    }
}
