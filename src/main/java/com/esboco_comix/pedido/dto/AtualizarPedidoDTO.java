package com.esboco_comix.pedido.dto;

import com.esboco_comix.pedido.dominio.enuns.StatusPedido;

public record AtualizarPedidoDTO (
    int id,
    StatusPedido status,
    boolean retornarAoEstoque
) {}
