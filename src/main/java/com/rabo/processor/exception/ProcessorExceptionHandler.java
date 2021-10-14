package com.rabo.processor.exception;

import com.rabo.processor.domain.Error;
import org.apache.tomcat.util.http.fileupload.impl.SizeLimitExceededException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ProcessorExceptionHandler {

    @ExceptionHandler(ProcessorException.class)
    public ResponseEntity<Error> handleProcessorException(RuntimeException exp) {
        Error error = new Error();
        error.setErrorCode(10101);
        error.setErrorDescription(exp.getMessage());
        error.setErrorCause(exp.getCause().toString());
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(SizeLimitExceededException.class)
    public ResponseEntity<Error> handleSizeLimitException(RuntimeException exp) {
        Error error = new Error();
        error.setErrorCode(10102);
        error.setErrorDescription(exp.getMessage());
        error.setErrorCause(exp.getCause().toString());
        return ResponseEntity.badRequest().body(error);
    }

}
