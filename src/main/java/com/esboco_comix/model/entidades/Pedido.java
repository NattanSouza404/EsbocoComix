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
    private String status;

    private Endereco enderecoEntrega;
    private List<ItemPedido> itensPedido = new ArrayList<>();
}
