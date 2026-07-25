package com.esboco_comix.pedido.dominio;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

import com.esboco_comix.pedido.dominio.enuns.StatusItemPedido;
import com.esboco_comix.pedido.dominio.enuns.TipoPedidoPosVenda;

@Getter
@Setter
public class PedidoPosVenda {
    private int id;
    private int idPedido;
    private int idQuadrinho;
    private int quantidade;
    private StatusItemPedido status;
    private TipoPedidoPosVenda tipo;
    private LocalDateTime data;
}
