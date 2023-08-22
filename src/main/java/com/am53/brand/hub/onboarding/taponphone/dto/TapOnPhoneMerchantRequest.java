package com.am53.brand.hub.onboarding.taponphone.dto;

import com.am53.brand.hub.onboarding.taponphone.utils.EnumTapOnPhoneMerchant;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class TapOnPhoneMerchantRequest {

    private String midPaycore;
    @JsonProperty("codigoTerminal")
    private String tidPaycore;
    private EnumTapOnPhoneMerchant status;

}
