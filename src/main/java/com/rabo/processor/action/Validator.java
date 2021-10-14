package com.rabo.processor.action;

import com.rabo.processor.domain.Report;
import com.rabo.processor.domain.FileRecord;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Validator {

    public static List<Report> validate(List<FileRecord> fileRecords) {
        Map<Long, String> record = new HashMap<>();
        List<Report> validationReports = new ArrayList<>();
        for (FileRecord fileRecord : fileRecords) {
            if(!isUniqueTransactionReference(record, fileRecord) || !isEndBalanceCorrect(fileRecord)) {
                validationReports.add(new Report(fileRecord.getTransactionRef(), fileRecord.getDescription()));
            }
        }
        return validationReports;
    }

    private static boolean isUniqueTransactionReference(Map<Long, String> record, FileRecord fileRecord) {
        if(record.containsKey(fileRecord.getTransactionRef())) {
            return false;
        } else {
            record.put(fileRecord.getTransactionRef(), fileRecord.getDescription());
            return true;
        }
    }

    private static boolean isEndBalanceCorrect(FileRecord fileRecord) {
        return fileRecord.getStartBalance().add(fileRecord.getMutation()).equals(fileRecord.getEndBalance());
    }
}
