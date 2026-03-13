# ai-pdf-summarizer
Spring Boot backend application that analyzes PDF documents and generates AI-powered summaries using OpenAI.

# Project Title
AI Document Summarizer – Spring Boot + OpenAI + PDF Processing

# Project Description
AI Document Summarizer is a backend application built using Spring Boot that accepts multiple PDF documents, extracts their content, and generates an AI-powered summary using the OpenAI API. The summarized content is returned as a structured response and can also be downloaded as a formatted PDF report.

This project demonstrates integration between document processing, REST APIs, and AI services, along with proper validation and exception handling.

# Features
        Upload up to 5 PDF documents
        Validates file count and file type
        Extracts text from PDF files using Apache PDFBox
        Generates AI-powered summaries using OpenAI API
        Returns structured summary response
        Download summarized content as a generated PDF
        Custom exception handling for invalid requests
        Clean layered Spring Boot architecture

# Tech Stack
        Java 17
        Spring Boot
        OpenAI API
        Apache PDFBox (PDF text extraction)
        OpenPDF / iText (PDF generation)
        Jackson (JSON parsing)
        Maven
        REST APIs

# Project Architecture
Controller Layer
    │
    ▼
DocumentController
    │
    ▼
Service Layer
    ├── DocumentService
    ├── OpenAIService
    └── PdfGeneratorService
    │
    ▼
Utility Layer
    └── FileValidator
    │
    ▼
Exception Layer
    ├── FileLimitExceededException
    ├── NoFileUploadedException
    └── InvalidFileTypeException

# API Endpoints
  # 1️⃣ Upload and Summarize Documents
    **POST**
      /api/documents/summarize
    **REQUEST**
      Multipart form DATA
      files: PDF files
    **Validation Rules**
        Minimum 1 file
        Maximum 5 files
        Only PDF format allowed
    **Response Example**
    {
        "summary": {
        "topic": "Artificial Intelligence",
        "key_points": [ "AI enables machines to simulate human intelligence", "Used in healthcare, finance, and automation"]
        }
    }

  # 2️⃣ Download Summary as PDF
    **GET**
      /api/documents/download
    **RESPONSE**
      Downloads a generated PDF file containing the summarized content.

# How it Works
Upload PDF files
        │
        ▼
File Validation
        │
        ▼
PDF Text Extraction (PDFBox)
        │
        ▼
OpenAI API Summarization
        │
        ▼
Return Summary JSON
        │
        ▼
Generate Downloadable PDF

# Author

Devi Priyanka
Java Backend Developer
Experienced in Java, Spring Boot, Microservices, Cloud Technologies
