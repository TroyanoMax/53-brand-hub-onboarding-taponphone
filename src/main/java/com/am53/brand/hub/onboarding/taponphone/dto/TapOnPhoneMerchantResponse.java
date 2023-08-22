package com.am53.brand.hub.onboarding.taponphone.dto;

import com.am53.brand.hub.onboarding.taponphone.config.DefaultIdValue;
import com.am53.brand.hub.onboarding.taponphone.utils.EnumTapOnPhoneMerchant;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sun.jdi.LongValue;
import lombok.Data;

import java.util.Date;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class TapOnPhoneMerchantResponse {

    @DefaultIdValue
    private int id;
    private String midPaycore;
    private String codigoTerminal;
    private EnumTapOnPhoneMerchant status;
    private Date createDate;

}