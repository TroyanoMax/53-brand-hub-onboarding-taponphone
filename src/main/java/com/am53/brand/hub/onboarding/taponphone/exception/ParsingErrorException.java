package com.am53.brand.hub.onboarding.taponphone.exception;

public class ParsingErrorException  extends Exception {
    public ParsingErrorException(String mensaje) {
        super(mensaje);
    }

    public ParsingErrorException(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }
}