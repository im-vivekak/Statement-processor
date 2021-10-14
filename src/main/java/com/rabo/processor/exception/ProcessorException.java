package com.rabo.processor.exception;

public class ProcessorException extends RuntimeException {

    public ProcessorException(String errorMessage) {
        super(errorMessage);
    }

    public ProcessorException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
