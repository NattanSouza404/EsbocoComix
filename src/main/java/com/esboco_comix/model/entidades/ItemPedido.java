package com.esboco_comix.model.entidades;

import com.esboco_comix.model.enuns.StatusItemPedido;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemPedido {
    private int idPedido;
    private int idQuadrinho;
    private int quantidade;
    private StatusItemPedido status;
}
