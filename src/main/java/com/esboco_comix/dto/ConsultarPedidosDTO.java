package com.esboco_comix.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ConsultarPedidosDTO {
    private List<PedidoDTO> pedidos;
    private List<ItemPedidoDTO> itensPedido;
}
