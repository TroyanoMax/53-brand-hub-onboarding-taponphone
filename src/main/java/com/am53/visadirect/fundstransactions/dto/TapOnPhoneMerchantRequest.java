package com.am53.visadirect.fundstransactions.dto;

import com.am53.visadirect.fundstransactions.utils.EnumTapOnPhoneMerchant;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class TapOnPhoneMerchantRequest {

    private String midPaycore;
    private String codigoTerminal;
    private EnumTapOnPhoneMerchant status;

}
