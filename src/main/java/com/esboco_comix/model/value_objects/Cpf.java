package com.esboco_comix.model.value_objects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public record Cpf(String valor) {

    @JsonCreator
    public Cpf {
        if (valor == null || valor.isBlank()) {
            throw new IllegalArgumentException("CPF não pode ser nulo ou vazio.");
        }

        valor = valor.replaceAll("\\D", "");

        if (valor.length() != 11) {
            throw new IllegalArgumentException("CPF deve possuir 11 dígitos.");
        }

        if (!valor.matches("\\d{11}")) {
            throw new IllegalArgumentException("CPF inválido.");
        }
    }

    @JsonValue
    public String valor() {
        return valor;
    }
}