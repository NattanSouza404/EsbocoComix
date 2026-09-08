package com.esboco_comix.cliente.dominio.value_objects;

public record Senha(String valor) {
    public Senha {
        if (valor == null || valor.isBlank()) {
            throw new IllegalArgumentException("Senha não pode ser nula ou vazia!");
        }

        if (valor.length() < 8 || valor.length() > 64){
            throw new IllegalArgumentException("Senha deve ter entre 8 e 64 caracteres!");
        }

        if (!(valor.matches(".*[A-Z].*"))){
            throw new IllegalArgumentException("Senha deve conter pelo menos um caractere maiúsculo!");
        }

        if (!(valor.matches(".*[a-z].*"))){
            throw new IllegalArgumentException("Senha deve conter pelo menos um caractere minúsculo!");
        }

        if (!(valor.matches(".*[!@#$%^&*(),.?\":{}|<>].*"))){
            throw new IllegalArgumentException("Senha deve conter pelo menos um caractere especial!");
        }
    }
}