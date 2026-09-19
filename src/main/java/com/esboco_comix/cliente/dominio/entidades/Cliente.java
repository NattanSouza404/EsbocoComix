package com.esboco_comix.cliente.dominio.entidades;

import java.time.LocalDate;
import java.util.List;

import com.esboco_comix.cliente.dominio.CriptografadorSenha;
import com.esboco_comix.cliente.dominio.enuns.Genero;
import com.esboco_comix.cliente.dominio.value_objects.Cpf;
import com.esboco_comix.cliente.dominio.value_objects.Email;
import com.esboco_comix.cliente.dominio.value_objects.Senha;
import com.esboco_comix.cliente.dominio.value_objects.Telefone;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
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
    
    private Integer ranking = 0;

    @JsonProperty("isAtivo")
    private Boolean isAtivo = true;

    private Telefone telefone;
    private List<CartaoCredito> cartoesCredito;
    private List<Endereco> enderecos;

    @Builder
    public Cliente(
        Integer id,
        String  nome,
        Genero  genero,
        LocalDate dataNascimento,
        Cpf cpf,
        Email email,
        Integer ranking,
        Boolean isAtivo,
        Telefone telefone,
        String hashSenha,
        String saltSenha
    ) {
        validarNome(nome);
        validarGenero(genero);
        validarDataNascimento(dataNascimento);

        this.id = id;
        this.nome = nome;
        this.genero = genero;
        this.dataNascimento = dataNascimento;
        this.cpf = cpf;
        this.email = email;
        this.ranking = ranking;
        this.isAtivo = isAtivo;
        this.telefone = telefone;
        this.hashSenha = hashSenha;
        this.saltSenha = saltSenha;
    }

    public void atualizar(
        String nome,
        Genero genero,
        LocalDate dataNascimento,
        Cpf cpf,
        Email email,
        Telefone telefone
    ){
        validarNome(nome);
        validarGenero(genero);
        validarDataNascimento(dataNascimento);

        this.nome = nome;
        this.genero = genero;
        this.dataNascimento = dataNascimento;
        this.cpf = cpf;
        this.email = email;
        this.telefone = telefone;
    }

    public void definirSenha(
        Senha senha,
        Senha senhaConfirmacao,
        CriptografadorSenha criptografador
    ) { 
        senha.validarSenhaConfirmacao(senhaConfirmacao);

        String saltSenha = criptografador.generateSalt();
        
        this.hashSenha = criptografador.hashSenha(senha, saltSenha);
        this.saltSenha = saltSenha;
    }

    public void alterarSenha(
        Senha senhaAntiga,
        Senha senhaNova,
        Senha senhaConfirmacao,
        CriptografadorSenha criptografador
    ) {
        String hashSenhaInformada = criptografador.hashSenha(
            senhaAntiga,
            this.getSaltSenha()
        );

        if (!(this.getHashSenha().equals(hashSenhaInformada))) {
            throw new IllegalArgumentException(
                "Senha antiga incorreta!"
            );
        }

        this.definirSenha(senhaNova, senhaConfirmacao, criptografador);
    }

    private void validarNome(String nome){
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("Nome do cliente não pode ser nulo ou vazio!");
        }

        if (nome.length() > 100){
            throw new IllegalArgumentException("Nome deve conter menos de 100 caracteres!");
        }
    }

    private void validarGenero(Genero genero){
        if (genero == null) {
            throw new IllegalArgumentException("Gênero do cliente não pode ser nulo!");
        }
    }

    private void validarDataNascimento(LocalDate dataNascimento){
        if (dataNascimento == null) {
            throw new IllegalArgumentException("Data de nascimento do cliente não pode ser nula!");
        }
    }
}
