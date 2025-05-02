package com.esboco_comix.model.entidades;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EntradaEstoque {
    private int id;
    private int idQuadrinho;
    private LocalDateTime dataEntrada;
    private int quantidade;
    private double valorCusto;
    private String fornecedor;
}
