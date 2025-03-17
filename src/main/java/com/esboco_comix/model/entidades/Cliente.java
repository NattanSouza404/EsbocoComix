package com.esboco_comix.model.entidades;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Cliente {
    private Integer id;
    private String  nome;
    private String  genero;
    private LocalDate dataNascimento;
    private String  cpf;
    private String  email;
    
    private String hashSenha;
    private String saltSenha;
    
    private Integer ranking;

    @JsonProperty("isAtivo")
    private Boolean isAtivo;

    private Telefone telefone;
    private List<CartaoCredito> cartoesCredito;
    private List<Endereco> enderecos;
}
