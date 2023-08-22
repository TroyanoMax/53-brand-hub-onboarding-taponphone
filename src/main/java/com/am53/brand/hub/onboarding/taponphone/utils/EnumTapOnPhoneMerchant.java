package com.am53.brand.hub.onboarding.taponphone.utils;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EnumTapOnPhoneMerchant {

    Inactive(0),
    Active(1);

    private Integer status;

    public static EnumTapOnPhoneMerchant fromStatus(Integer status) {
        for (EnumTapOnPhoneMerchant enumValue : EnumTapOnPhoneMerchant.values()) {
            if (enumValue.status.equals(status)) {
                return enumValue;
            }
        }
        throw new IllegalArgumentException("Invalid status: " + status);
    }

}