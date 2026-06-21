package com.esboco_comix.model.value_objects;

import com.esboco_comix.model.enuns.TipoTelefone;

public record Telefone(
    String ddd,
    String numero,
    TipoTelefone tipo
) {
    public Telefone {
        if (ddd == null || ddd.isBlank()) {
            throw new IllegalArgumentException("DDD não pode ser nulo ou vazio!");
        }

        ddd = ddd.trim();

        if (ddd.length() != 2) {
            throw new IllegalArgumentException("DDD deve ter 2 caracteres!");
        }

        if (!ddd.matches("\\d+")) {
            throw new IllegalArgumentException("DDD só pode conter números!");
        }

        if (numero == null || numero.isBlank()) {
            throw new IllegalArgumentException("Número de telefone não pode ser nulo ou vazio!");
        }

        numero = numero.trim();

        if (numero.length() < 8 || numero.length() > 9) {
            throw new IllegalArgumentException("Número de telefone deve ter entre 8 e 9 caracteres!");
        }

        if (!numero.matches("\\d+")) {
            throw new IllegalArgumentException("Número de telefone só pode conter números!");
        }

        if (tipo == null) {
            throw new IllegalArgumentException("Tipo de telefone não pode ser nulo!");
        }
    }
}
