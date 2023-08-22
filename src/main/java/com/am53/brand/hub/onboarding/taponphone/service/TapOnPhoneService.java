package com.am53.brand.hub.onboarding.taponphone.service;

import com.am53.brand.hub.onboarding.taponphone.dto.TapOnPhoneMerchantRequest;
import com.am53.brand.hub.onboarding.taponphone.dto.TapOnPhoneMerchantResponse;

public interface TapOnPhoneService {

    TapOnPhoneMerchantRequest createOrUpdateTapOnPhone(TapOnPhoneMerchantRequest request);

    TapOnPhoneMerchantResponse findTapOnPhone(String midPaycore);
}
