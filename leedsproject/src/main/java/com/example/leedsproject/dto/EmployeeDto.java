package com.example.leedsproject.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class EmployeeDto {

    @JacksonXmlProperty(isAttribute = true)
    private String id;
    private String firstname;
    private String lastname;
    private String title;
    private String division;
    private String building;
    private String room;
}
