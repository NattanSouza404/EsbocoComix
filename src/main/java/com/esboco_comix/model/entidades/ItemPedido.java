package com.esboco_comix.model.entidades;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemPedido {
    private int idPedido;
    private int idQuadrinho;
    private int quantidade;

    // TODO retirar futuramente
    private double preco;
    private String nome;
}
