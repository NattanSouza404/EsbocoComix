package com.fatec.model.entidades;

import java.time.LocalDate;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Cliente {
    private int     id;
    private String  nome;
    private Genero  genero;
    private String  cpf;
    private String  email;
    private LocalDate dataNascimento;
    private String senha;

    private Telefone telefone;
    private List<CartaoCredito> cartoesCliente;
    private List<Endereco> enderecos;
}
