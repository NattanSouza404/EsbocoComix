package com.esboco_comix.dto;

import java.time.LocalDateTime;

import com.esboco_comix.model.entidades.PedidoPosVenda;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PedidoPosVendaDTO {
    @JsonUnwrapped
    private PedidoPosVenda pedidoPosVenda;

    private String nomeCliente;
    private String nomeQuadrinho;
    private LocalDateTime data;
}
