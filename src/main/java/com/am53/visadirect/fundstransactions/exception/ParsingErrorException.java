package com.am53.visadirect.fundstransactions.exception;

public class ParsingErrorException  extends Exception {
    public ParsingErrorException(String mensaje) {
        super(mensaje);
    }

    public ParsingErrorException(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }
}