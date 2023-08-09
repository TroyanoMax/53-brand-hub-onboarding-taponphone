package com.am53.visadirect.fundstransactions.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ApiFilterDto {
    
    private String key;
    private String value;
    private String operation;
    private String union;

}
