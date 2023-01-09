package com.luiz.helpdesk.domain.enuns;

public enum Perfil {

    ADMIN(0, "ROLE_ADMIN"), CLIENTE(1, "ROLE_CLIENTE"), TECNICO(2, "ROLE_TECNICO");

    private int code;
    private String description;

    Perfil(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public static Perfil toEnum(Integer code) {

        if(code == null) {
            return null;
        }

        for(Perfil perfil : Perfil.values()) {
            if(code.equals(perfil.getCode())) {

                return perfil;

            }
        }

        throw new IllegalArgumentException("Perfil inválido!");

    }

}




