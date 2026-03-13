package com.devi.ai.ai_document_summariser.utility;

import com.devi.ai.ai_document_summariser.exception.FileLimitExceededException;
import com.devi.ai.ai_document_summariser.exception.InvalidFileException;
import com.devi.ai.ai_document_summariser.exception.NoFileUploadedException;
import org.springframework.web.multipart.MultipartFile;

public class FileValidationUtility {


    public void validateFiles(MultipartFile[] files) {

        boolean allFilesEmpty = true;

        for (MultipartFile file : files) {
            if (!file.isEmpty()) {
                allFilesEmpty = false;
                break;
            }
        }

        if (files == null || files.length == 0 || allFilesEmpty) {
            throw new NoFileUploadedException("At least one valid file must be uploaded.");
        }

        if (files.length > 5) {
            throw new FileLimitExceededException("Maximum 5 files are allowed.");
        }

        for (MultipartFile file : files) {
            if (!file.getContentType().equals("application/pdf")) {
                throw new InvalidFileException("Only PDF files allowed");
            }
        }
    }
}
