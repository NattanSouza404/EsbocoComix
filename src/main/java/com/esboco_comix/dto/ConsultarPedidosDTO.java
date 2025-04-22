package com.esboco_comix.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ConsultarPedidosDTO {
    private List<PedidoDTO> pedidos;
    private List<ItemPedidoDTO> itensPedido;
}
