package com.rabo.processor.handler;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import com.rabo.processor.domain.FileRecord;
import com.rabo.processor.domain.XmlFileInput;
import com.rabo.processor.exception.ProcessorException;
import com.rabo.processor.mapper.StatementFileMapper;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public class XmlHandler implements FileHandler{

    @Override
    public List<FileRecord> process(MultipartFile file) {
        XmlFileInput xmlFileInput;
        try {
            XmlMapper xmlMapper = new XmlMapper();
            xmlFileInput = xmlMapper.readValue(file.getInputStream(), XmlFileInput.class);
        } catch (IOException | NullPointerException  exp) {
            throw new ProcessorException("Unable to parse CSV file", exp);
        }
        return StatementFileMapper.convertXmlRecordToStatementRecord(xmlFileInput.getXmlRecords());
    }
}
