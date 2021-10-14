package com.rabo.processor.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabo.processor.domain.FileProcessorResponse;
import com.rabo.processor.service.FileProcessorService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ProcessorControllerTest {
    private MockMvc mockMvc;
    private ObjectMapper mapper;
    @Mock
    private FileProcessorService fileProcessorService;
    @InjectMocks
    private ProcessorController processorController;

    @Before
    public void setup() {
       mapper = new ObjectMapper();
    }


    @Test
    public void testValidateFlowAndFileUpload() throws IOException {
        MockMultipartFile mockMultipartFile = new MockMultipartFile("file", "records.csv", "text/csv", this.getClass().getResourceAsStream("/records.csv"));
        FileProcessorResponse fileProcessorResponse = mapper.readValue(this.getClass().getResourceAsStream("/responseCsv.json"), FileProcessorResponse.class);
        when(fileProcessorService.processFile(mockMultipartFile)).thenReturn(fileProcessorResponse);
        ResponseEntity<FileProcessorResponse> response = processorController.validate(mockMultipartFile);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(fileProcessorResponse, response.getBody());
    }
}