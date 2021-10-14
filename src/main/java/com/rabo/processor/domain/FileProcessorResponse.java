package com.rabo.processor.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
public class FileProcessorResponse {
    private List<Report> reports;
}
