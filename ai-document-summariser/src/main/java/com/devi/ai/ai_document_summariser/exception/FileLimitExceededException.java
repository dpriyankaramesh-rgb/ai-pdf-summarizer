package com.devi.ai.ai_document_summariser.exception;

public class FileLimitExceededException extends RuntimeException {

    public FileLimitExceededException() {
        super();
    }

    public FileLimitExceededException(String message) {
        super(message);
    }

    public FileLimitExceededException(String message, Throwable cause) {
        super(message, cause);
    }

}
