package com.esboco_comix.dto;

import com.esboco_comix.model.entidades.Quadrinho;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonUnwrapped;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QuadrinhoDTO {
    @JsonUnwrapped
    private Quadrinho quadrinho;

    private int quantidadeEstoque;
    @JsonProperty("isForaDeEstoque")
    private boolean isForaDeEstoque;
}
