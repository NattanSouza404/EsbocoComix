package com.esboco_comix.pedido.dto;

import com.esboco_comix.pedido.dominio.ItemPedido;
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
    private String urlImagem;
}
