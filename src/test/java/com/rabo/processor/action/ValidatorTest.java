package com.rabo.processor.action;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabo.processor.domain.FileRecord;
import com.rabo.processor.domain.Report;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class ValidatorTest {

    private ObjectMapper mapper;

    @Before
    public void setup() {
        mapper = new ObjectMapper();
    }


    @Test
    public void testValidation() throws IOException {
        List<FileRecord> fileRecords = new ArrayList<>();
        FileRecord record = new FileRecord();
        record.setDescription("Flowers for Erik Bakker");
        record.setAccountNumber("NL56RABO0149876948");
        record.setMutation(BigDecimal.valueOf(-45.33));
        record.setTransactionRef(132843L);
        record.setEndBalance(BigDecimal.valueOf(45.35));
        record.setStartBalance(BigDecimal.valueOf( 90.68));
        FileRecord record1 = new FileRecord();
        record1.setDescription("Candy for Erik Bakker");
        record1.setAccountNumber("NL56RABO0149876948");
        record1.setMutation(BigDecimal.valueOf(-45.33));
        record1.setTransactionRef(132843L);
        record1.setEndBalance(BigDecimal.valueOf(45.35));
        record1.setStartBalance(BigDecimal.valueOf( 90.68));
        FileRecord record2 = new FileRecord();
        record2.setDescription("Candy for Erik Bakker");
        record2.setAccountNumber("NL56RABO0149876948");
        record2.setMutation(BigDecimal.valueOf(-45.33));
        record2.setTransactionRef(132844L);
        record2.setEndBalance(BigDecimal.valueOf(45.34));
        record2.setStartBalance(BigDecimal.valueOf( 90.68));

        fileRecords.add(record);
        fileRecords.add(record1);
        fileRecords.add(record2);

        List<Report> result = Validator.validate(fileRecords);
        assertEquals(result.size(), 2);
        assertEquals(result.get(0).getTransactionRef(), 132843L);
        assertEquals(result.get(1).getTransactionRef(), 132844L);
    }
}