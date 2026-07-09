package com.esboco_comix.dto;

import com.esboco_comix.model.enuns.StatusPedido;

public record AtualizarPedidoDTO (
    int id,
    StatusPedido status,
    boolean retornarAoEstoque
) {}
