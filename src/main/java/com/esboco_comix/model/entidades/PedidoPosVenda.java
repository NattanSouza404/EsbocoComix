package com.esboco_comix.model.entidades;

import com.esboco_comix.model.enuns.StatusItemPedido;
import com.esboco_comix.model.enuns.TipoPedidoPosVenda;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

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
