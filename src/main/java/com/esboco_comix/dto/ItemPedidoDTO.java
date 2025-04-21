package com.esboco_comix.dto;

import com.esboco_comix.model.entidades.ItemPedido;
import com.fasterxml.jackson.annotation.JsonUnwrapped;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemPedidoDTO {
    @JsonUnwrapped
    private ItemPedido itemPedido;
    
    private String nomeQuadrinho;
    private String nomeCliente;
}
