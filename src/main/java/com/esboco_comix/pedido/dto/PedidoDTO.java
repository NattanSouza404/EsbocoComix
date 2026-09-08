package com.esboco_comix.pedido.dto;

import java.util.List;

import com.esboco_comix.pedido.dominio.Pedido;
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
    private List<ItemPedidoDTO> itensPedidoDTO;
}
