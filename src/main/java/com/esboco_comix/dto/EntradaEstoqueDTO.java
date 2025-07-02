package com.esboco_comix.dto;

import com.esboco_comix.model.entidades.EntradaEstoque;
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
