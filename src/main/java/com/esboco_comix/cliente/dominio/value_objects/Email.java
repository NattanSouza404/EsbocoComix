package com.esboco_comix.cliente.dominio.value_objects;

import java.util.regex.Pattern;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public record Email(
    String valor
) {

    private static final Pattern REGEX_EMAIL = Pattern.compile(
        "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$"
    );

    @JsonCreator
    public Email {
        if (valor == null || valor.isBlank()) {
            throw new IllegalArgumentException("Email não pode ser nulo ou vazio!");
        }

        valor = valor.trim();

        if (!REGEX_EMAIL.matcher(valor).matches()) {
            throw new IllegalArgumentException("Email inválido!");
        }
    }

    @JsonValue
    public String valor() {
        return valor;
    }
}

