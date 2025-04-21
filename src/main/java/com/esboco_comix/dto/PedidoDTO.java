package com.esboco_comix.dto;

import com.esboco_comix.model.entidades.Pedido;
import com.fasterxml.jackson.annotation.JsonUnwrapped;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PedidoDTO {
    @JsonUnwrapped
    private Pedido pedido;
    
    private String nomeCliente;
    private double valorTotal;
}
