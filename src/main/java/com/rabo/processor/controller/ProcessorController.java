package com.rabo.processor.controller;

import com.rabo.processor.domain.FileProcessorResponse;
import com.rabo.processor.service.FileProcessorService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("statement/api/")
public class ProcessorController {

    private FileProcessorService fileProcessorService;

    @Autowired
    public ProcessorController(FileProcessorService fileProcessorService) {
        this.fileProcessorService = fileProcessorService;
    }


    @PostMapping(value="validate")
    @ApiOperation(value = "Endpoint for validating customer statement", produces = "Application/JSON", response = FileProcessorResponse.class, httpMethod = "POST")
    public ResponseEntity<FileProcessorResponse> validate(@RequestParam("file") MultipartFile file) {
        FileProcessorResponse fileProcessorResponse = fileProcessorService.processFile(file);
        return ResponseEntity.ok(fileProcessorResponse);
    }
}
