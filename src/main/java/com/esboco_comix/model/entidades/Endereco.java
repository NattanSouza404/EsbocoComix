package com.esboco_comix.model.entidades;

import com.esboco_comix.model.enuns.TipoLogradouro;
import com.esboco_comix.model.enuns.TipoResidencial;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Endereco {
    private int id;
    private String fraseCurta;
    private String logradouro;
    private TipoLogradouro tipoLogradouro;
    private TipoResidencial tipoResidencial;
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

    @JsonProperty("isAtivo")
    private Boolean isAtivo;

    private int idCliente;
}
