package com.rabo.processor.domain;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@JacksonXmlRootElement(localName = "records")
@Setter
@Getter
@NoArgsConstructor
public class XmlFileInput {

    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "record")
    private List<XmlRecord> xmlRecords;

    @Setter
    @Getter
    @NoArgsConstructor
    public static class XmlRecord {
        @JacksonXmlProperty(localName = "reference", isAttribute = true) private String transactionRef;
        @JacksonXmlProperty private String accountNumber;
        @JacksonXmlProperty private String startBalance;
        @JacksonXmlProperty private String mutation;
        @JacksonXmlProperty private String description;
        @JacksonXmlProperty private String endBalance;
    }
}
