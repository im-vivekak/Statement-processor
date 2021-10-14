package com.rabo.processor.service;

import com.rabo.processor.domain.FileProcessorResponse;
import com.rabo.processor.exception.ProcessorException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.mock.web.MockMultipartFile;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(MockitoJUnitRunner.class)
public class FileProcessorServiceTest {

    private FileProcessorService fileProcessorService;

    @Before
    public void setup() {
        fileProcessorService = new FileProcessorService();
    }

    @Test
    public void testCsvFileProcessedSuccessFully() throws IOException {
        MockMultipartFile mockMultipartFile = new MockMultipartFile("file", "records.csv", "text/csv", this.getClass().getResourceAsStream("/records.csv"));
        FileProcessorResponse fileProcessorResponse= fileProcessorService.processFile(mockMultipartFile);
        assertEquals(fileProcessorResponse.getReports().size(), 2);
    }

    @Test(expected = ProcessorException.class)
    public void testExceptionForWrongFileFormat() throws IOException {
        MockMultipartFile mockMultipartFile = new MockMultipartFile("file", "records.csv", "multipart/form-data", this.getClass().getResourceAsStream("/records.csv"));
        fileProcessorService.processFile(mockMultipartFile);
    }

    @Test
    public void testXmlFileProcessedSuccessFully() throws IOException {
        MockMultipartFile mockMultipartFile = new MockMultipartFile("file", "records.xml", "application/xml", this.getClass().getResourceAsStream("/records.xml"));
        FileProcessorResponse fileProcessorResponse= fileProcessorService.processFile(mockMultipartFile);
        assertEquals( 3, fileProcessorResponse.getReports().size());
    }
}