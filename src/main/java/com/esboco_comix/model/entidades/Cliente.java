package com.esboco_comix.model.entidades;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Cliente {
    private int     id;
    private String  nome;
    private String  genero;
    private LocalDate dataNascimento;
    private String  cpf;
    private String  email;
    
    private String senha;
    private String senhaConfirmacao;
    private String hashSenha;
    private String saltSenha;
    
    private int ranking;

    @JsonProperty("isAtivo")
    private boolean isAtivo;

    private Telefone telefone;
    private List<CartaoCredito> cartoesCliente;
    private List<Endereco> enderecos;
}
