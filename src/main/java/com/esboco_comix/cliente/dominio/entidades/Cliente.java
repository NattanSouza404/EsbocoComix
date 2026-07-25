package com.esboco_comix.cliente.dominio.entidades;

import java.time.LocalDate;
import java.util.List;

import com.esboco_comix.cliente.dominio.enuns.Genero;
import com.esboco_comix.cliente.dominio.value_objects.Cpf;
import com.esboco_comix.cliente.dominio.value_objects.Email;
import com.esboco_comix.cliente.dominio.value_objects.Telefone;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Cliente {
    private Integer id;
    private String  nome;
    private Genero  genero;
    private LocalDate dataNascimento;
    private Cpf cpf;
    
    private Email email;

    private String hashSenha;
    private String saltSenha;
    
    private Integer ranking;

    @JsonProperty("isAtivo")
    private Boolean isAtivo;

    private Telefone telefone;
    private List<CartaoCredito> cartoesCredito;
    private List<Endereco> enderecos;

    public void validar(){
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("Nome do cliente não pode ser nulo ou vazio!");
        }

        if (nome.length() > 100){
            throw new IllegalArgumentException("Nome deve conter menos de 100 caracteres!");
        }

        if (genero == null) {
            throw new IllegalArgumentException("Gênero do cliente não pode ser nulo!");
        }

        if (dataNascimento == null) {
            throw new IllegalArgumentException("Data de nascimento do cliente não pode ser nula!");
        }
    }
}
