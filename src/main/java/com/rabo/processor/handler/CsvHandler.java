package com.rabo.processor.handler;

import com.opencsv.bean.CsvToBeanBuilder;
import com.rabo.processor.domain.CsvFileInput;
import com.rabo.processor.domain.FileRecord;
import com.rabo.processor.exception.ProcessorException;
import com.rabo.processor.mapper.StatementFileMapper;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

public class CsvHandler implements FileHandler{

    @Override
    public List<FileRecord> process(MultipartFile file) {
        List<CsvFileInput> statementCsvFileInputList = null;
        List<FileRecord> fileRecords;
        try {
            Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()));
            statementCsvFileInputList = new CsvToBeanBuilder<CsvFileInput>(reader)
                    .withType(CsvFileInput.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build()
                    .parse();
            fileRecords = StatementFileMapper.convertCsvFileInputToStatementRecord(statementCsvFileInputList);
        } catch (IOException | NullPointerException exp) {
           throw new ProcessorException("Unable to parse CSV file", exp);
        } catch(NumberFormatException exp) {
            throw new ProcessorException("Check the file data", exp);
        }
        return fileRecords;
    }
}
