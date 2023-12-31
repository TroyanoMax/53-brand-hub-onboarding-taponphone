package com.am53.brand.hub.onboarding.taponphone.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ApiResources {

    PULLFUNDSTRANSACTIONS("pullfundstransactions"),
    PUSHFUNDSTRANSACTIONS("pushfundstransactions"),
    REVERSEFUNDSTRANSACTIONS("reversefundstransactions");

    private final String literal;

}
