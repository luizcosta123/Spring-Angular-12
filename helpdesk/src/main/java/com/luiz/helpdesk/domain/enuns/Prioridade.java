package com.luiz.helpdesk.domain.enuns;

public enum Prioridade {

    BAIXA(0, "BAIXA"), MEDIA(1, "MEDIA"), ALTA(2, "ALTA");

    private int code;
    private String description;

    Prioridade(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public static Prioridade toEnum(Integer code) {

        if(code == null) {
            return null;
        }

        for(Prioridade perfil : Prioridade.values()) {
            if(code.equals(perfil.getCode())) {

                return perfil;

            }
        }

        throw new IllegalArgumentException("Prioridade inválido!");

    }

}




