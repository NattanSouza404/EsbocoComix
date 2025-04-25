package com.esboco_comix.dto;

import java.util.List;

import com.esboco_comix.model.entidades.Pedido;
import com.fasterxml.jackson.annotation.JsonUnwrapped;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PedidoDTO {
    @JsonUnwrapped
    private Pedido pedido;
    
    private String nomeCliente;
    private double valorTotal;
    private List<ItemPedidoDTO> itensPedidoDTO;
}
