package com.esboco_comix.model.entidades;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Cupom {
    private int id;
    private double valor;

    @JsonProperty("isPromocional")
    private boolean isPromocional;

    @JsonProperty("isTroca")
    private boolean isTroca;

    @JsonProperty("isAtivo")
    private boolean isAtivo;

    private int idCliente;
}
