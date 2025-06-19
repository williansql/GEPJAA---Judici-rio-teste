package com.teste.gepjaa.process;

public enum StatusEnum {

    ACTIVE("Ativo"),
    ARQUIVED("Arquivado"),
    SUSPENDED("Suspenso");

    private String value;

    StatusEnum(String value) {
        this.value = value;
    }

}
