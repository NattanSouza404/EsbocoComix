package com.esboco_comix.dto;

import com.esboco_comix.model.entidades.ItemPedido;
import com.fasterxml.jackson.annotation.JsonUnwrapped;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ItemPedidoDTO {
    @JsonUnwrapped
    private ItemPedido itemPedido;
    
    private String nomeQuadrinho;
    private String nomeCliente;
}
