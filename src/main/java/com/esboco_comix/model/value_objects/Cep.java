package com.esboco_comix.model.value_objects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public record Cep(String valor) {

    @JsonCreator
    public Cep {
        if (valor == null || valor.isBlank()) {
            throw new IllegalArgumentException("CEP não pode ser nulo ou vazio!");
        }

        if (valor.length() != 8){
            throw new IllegalArgumentException("CEP deve ter 8 caracteres!");
        }

        if (!valor.matches("\\d+")){
            throw new IllegalArgumentException("CEP só pode conter números!");
        }
    }

    @JsonValue
    public String valor() {
        return valor;
    }
}