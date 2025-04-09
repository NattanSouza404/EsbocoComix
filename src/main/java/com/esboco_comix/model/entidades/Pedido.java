package com.esboco_comix.model.entidades;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Pedido {
    private int id;
    private int idCliente;
    private StatusPedido status;
    private Double valorFrete;

    private List<ItemPedido> itensPedido = new ArrayList<>();
    private Endereco enderecoEntrega;
    // TODO private List<CartaoCreditoPedido> cartoesCreditoPedidos = new ArrayList<>();
    
}
