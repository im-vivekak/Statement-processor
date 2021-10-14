package com.rabo.processor.handler;

import com.rabo.processor.domain.FileRecord;
import com.rabo.processor.exception.ProcessorException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.mock.web.MockMultipartFile;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(MockitoJUnitRunner.class)
public class XmlHandlerTest {

    @Test
    public void processXmlFile() throws IOException {
        FileHandler csvProcessor = new XmlHandler();
        MockMultipartFile mockMultipartFile = new MockMultipartFile("file", "records.xml", "application/xml", this.getClass().getResourceAsStream("/records.xml"));
        List<FileRecord> fileRecords = csvProcessor.process(mockMultipartFile);
        assertEquals(10, fileRecords.size());
    }

    @Test(expected = NullPointerException.class)
    public void processFailureWrongData() throws IOException {
        FileHandler csvProcessor = new XmlHandler();
        MockMultipartFile mockMultipartFile = new MockMultipartFile("file", "invalidRecords.xml", "application/xml", this.getClass().getResourceAsStream("/invalidRecords.xml"));
        csvProcessor.process(mockMultipartFile);
    }
}