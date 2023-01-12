package com.luiz.helpdesk.exception;

public class DataIntegrityViolationExceptionChamado extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public DataIntegrityViolationExceptionChamado(String message) {
        super(message);
    }

    public DataIntegrityViolationExceptionChamado(String message, Throwable cause) {
        super(message, cause);
    }
}
