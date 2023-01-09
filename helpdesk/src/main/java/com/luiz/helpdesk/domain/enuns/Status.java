package com.luiz.helpdesk.domain.enuns;

public enum Status {

    ABERTO(0, "ABERTO"), ANDAMENTO(1, "ANDAMENTO"), ENCERRADO(2, "ENCERRADO");

    private int code;
    private String description;

    Status(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public static Status toEnum(Integer code) {

        if(code == null) {
            return null;
        }

        for(Status perfil : Status.values()) {
            if(code.equals(perfil.getCode())) {

                return perfil;

            }
        }

        throw new IllegalArgumentException("Status inválido!");

    }

}




