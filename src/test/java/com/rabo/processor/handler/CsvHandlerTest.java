package com.rabo.processor.handler;

import com.rabo.processor.domain.FileRecord;
import com.rabo.processor.exception.ProcessorException;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class CsvHandlerTest {

    @Test
    public void processCsvFile() throws IOException {
        FileHandler csvProcessor = new CsvHandler();
        MockMultipartFile mockMultipartFile = new MockMultipartFile("file", "records.csv", "multipart/form-data", this.getClass().getResourceAsStream("/records.csv"));
        List<FileRecord> fileRecords = csvProcessor.process(mockMultipartFile);
        assertEquals(10, fileRecords.size());
    }

    @Test(expected = ProcessorException.class)
    public void processFailureWrongData() throws IOException {
        FileHandler csvProcessor = new CsvHandler();
        MockMultipartFile mockMultipartFile = new MockMultipartFile("file", "records.csv", "multipart/form-data", this.getClass().getResourceAsStream("/invalidRecords.csv"));
        csvProcessor.process(mockMultipartFile);
    }

}