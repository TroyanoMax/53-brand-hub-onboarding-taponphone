package com.am53.visadirect.fundstransactions.service;

import com.am53.visadirect.fundstransactions.dto.TapOnPhoneMerchantRequest;
import com.fasterxml.jackson.databind.JsonNode;

public interface TapOnPhoneService {

    TapOnPhoneMerchantRequest createOrUpdateTapOnPhone(TapOnPhoneMerchantRequest request);
}
