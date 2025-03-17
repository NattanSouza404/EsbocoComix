package com.esboco_comix.model.entidades;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Endereco {
    private int id;
    private String fraseCurta;
    private String logradouro;
    private String tipoLogradouro;
    private String tipoResidencial;
    private String numero;
    private String bairro;
    private String cep;
    private String cidade;
    private String estado;
    private String pais;

    @JsonProperty("isResidencial")
    private Boolean isResidencial;

    @JsonProperty("isEntrega")
    private Boolean isEntrega;

    @JsonProperty("isCobranca")
    private Boolean isCobranca;

    private String observacoes;

    private int idCliente;
}
