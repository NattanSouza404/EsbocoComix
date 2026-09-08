package com.esboco_comix.quadrinho.dto;

import com.esboco_comix.quadrinho.dominio.Quadrinho;
import com.fasterxml.jackson.annotation.JsonUnwrapped;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QuadrinhoDTO {
    @JsonUnwrapped
    private Quadrinho quadrinho;

    private int quantidadeEstoque;
    private Double preco;
}
