package com.esboco_comix.estoque.dto;

import com.esboco_comix.estoque.dominio.EntradaEstoque;
import com.fasterxml.jackson.annotation.JsonUnwrapped;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EntradaEstoqueDTO {
    @JsonUnwrapped
    private EntradaEstoque entradaEstoque;

    private String nomeQuadrinho;
}
