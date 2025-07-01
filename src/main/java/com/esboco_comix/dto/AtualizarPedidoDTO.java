package com.esboco_comix.dto;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AtualizarPedidoDTO {
    @JsonUnwrapped
    private PedidoDTO pedido;

    private boolean retornarAoEstoque;
}
