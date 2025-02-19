package com.fatec.modelo.entidades;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Cliente {
    private int         id;
    private String      nome;
    private String genero;

    private List<Telefone> telefones;
    private List<Cartao> cartoesCliente;
    private List<Endereco> enderecosCobranca;
    private List<Endereco> enderecosResidenciais;

    

}
