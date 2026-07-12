package com.esboco_comix.dto;

import com.esboco_comix.model.value_objects.Telefone;

public record AtualizarClienteDTO(
    int id,
    String nome,
    String genero,
    String dataNascimento,
    String cpf,
    String email,
    Telefone telefone
) {}