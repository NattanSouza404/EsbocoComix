package com.esboco_comix.pedido.dto;

import com.esboco_comix.pedido.dominio.PedidoPosVenda;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AtualizarPedidoPosVendaDTO {
    @JsonUnwrapped
    private PedidoPosVenda pedidoPosVenda;

    private boolean retornarAoEstoque;
}
