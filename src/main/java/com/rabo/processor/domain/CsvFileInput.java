package com.rabo.processor.domain;

import com.opencsv.bean.CsvBindByName;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class CsvFileInput {
    @CsvBindByName(column = "reference")
    private String transactionRef;
    @CsvBindByName(column = "Account Number")
    private String accountNumber;
    @CsvBindByName(column = "Start Balance")
    private String startBalance;
    @CsvBindByName(column = "Mutation")
    private String mutation;
    @CsvBindByName(column = "Description")
    private String description;
    @CsvBindByName(column = "End Balance")
    private String endBalance;
}
