package com.esboco_comix.cliente.dto;

import java.time.LocalDate;
import java.util.List;

import com.esboco_comix.cliente.dominio.entidades.CartaoCredito;
import com.esboco_comix.cliente.dominio.entidades.Endereco;
import com.esboco_comix.cliente.dominio.enuns.Genero;
import com.esboco_comix.cliente.dominio.value_objects.Telefone;

import lombok.Builder;

@Builder
public record CadastrarClienteDTO(
    String  nome,
    Genero  genero,
    LocalDate dataNascimento,
    String cpf,
    String email,

    Telefone telefone,

    List<Endereco> enderecos,
    List<CartaoCredito> cartoesCredito,

    String senhaNova,
    String senhaConfirmacao
){}
