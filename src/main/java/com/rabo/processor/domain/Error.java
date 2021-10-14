package com.rabo.processor.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class Error {
    private int errorCode;
    private String errorDescription;
    private String errorCause;
}
