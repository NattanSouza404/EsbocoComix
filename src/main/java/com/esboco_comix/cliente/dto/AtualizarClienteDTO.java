package com.esboco_comix.cliente.dto;

import com.esboco_comix.cliente.dominio.value_objects.Telefone;

public record AtualizarClienteDTO(
    int id,
    String nome,
    String genero,
    String dataNascimento,
    String cpf,
    String email,
    Telefone telefone
) {}