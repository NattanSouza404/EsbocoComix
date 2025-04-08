package com.esboco_comix.model.entidades;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartaoCreditoPedido {
    private int idPedido;
    private int idCartaoCredito;
    private double valor;
}
