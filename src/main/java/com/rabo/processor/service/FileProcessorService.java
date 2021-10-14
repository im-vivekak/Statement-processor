package com.rabo.processor.service;

import com.rabo.processor.action.Validator;
import com.rabo.processor.domain.FileProcessorResponse;
import com.rabo.processor.domain.FileType;
import com.rabo.processor.domain.FileRecord;
import com.rabo.processor.exception.ProcessorException;
import com.rabo.processor.handler.FileHandler;
import com.rabo.processor.handler.FileHandlerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Objects;

@Service
public class FileProcessorService {

    public FileProcessorResponse processFile(MultipartFile file) {
        List<FileRecord> statementFileRecords;
        FileHandler fileHandler = FileHandlerFactory.getFileHandler(FileType.findByKey(file.getContentType()));
        try {
            statementFileRecords = Objects.requireNonNull(fileHandler).process(file);
        } catch(NullPointerException exp) {
            throw new ProcessorException("Check the format of the file uploaded. CSV and XML are only supported", exp);
        }
        FileProcessorResponse fileProcessorResponse = new FileProcessorResponse();
        fileProcessorResponse.setReports(Validator.validate(statementFileRecords));
        return fileProcessorResponse;
    }

}
