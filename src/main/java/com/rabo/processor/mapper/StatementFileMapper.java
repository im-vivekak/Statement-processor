package com.rabo.processor.mapper;

import com.rabo.processor.domain.CsvFileInput;
import com.rabo.processor.domain.FileRecord;
import com.rabo.processor.domain.XmlFileInput;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public final class StatementFileMapper {

    public static List<FileRecord> convertCsvFileInputToStatementRecord(List<CsvFileInput> statementCsvFileInputList) {
        List<FileRecord> statementFileRecords = new ArrayList<>();
        for (CsvFileInput csvFileInput : statementCsvFileInputList) {
            FileRecord fileRecord = new FileRecord();
            fileRecord.setTransactionRef(Long.valueOf(csvFileInput.getTransactionRef()));
            fileRecord.setAccountNumber(csvFileInput.getAccountNumber());
            fileRecord.setDescription(csvFileInput.getDescription());
            fileRecord.setStartBalance(new BigDecimal(csvFileInput.getStartBalance()));
            fileRecord.setMutation(new BigDecimal(csvFileInput.getMutation()));
            fileRecord.setEndBalance(new BigDecimal(csvFileInput.getEndBalance()));
            statementFileRecords.add(fileRecord);
        }
       return statementFileRecords;
    }

    public static List<FileRecord> convertXmlRecordToStatementRecord(List<XmlFileInput.XmlRecord> xmlRecords) {
        List<FileRecord> statementFileRecords = new ArrayList<>();
        for (XmlFileInput.XmlRecord xmlRecord : xmlRecords) {
            FileRecord fileRecord = new FileRecord();
            fileRecord.setTransactionRef(Long.valueOf(xmlRecord.getTransactionRef()));
            fileRecord.setAccountNumber(xmlRecord.getAccountNumber());
            fileRecord.setDescription(xmlRecord.getDescription());
            fileRecord.setStartBalance(new BigDecimal(xmlRecord.getStartBalance()));
            fileRecord.setMutation(new BigDecimal(xmlRecord.getMutation()));
            fileRecord.setEndBalance(new BigDecimal(xmlRecord.getEndBalance()));
            statementFileRecords.add(fileRecord);
        }
        return statementFileRecords;
    }
}
