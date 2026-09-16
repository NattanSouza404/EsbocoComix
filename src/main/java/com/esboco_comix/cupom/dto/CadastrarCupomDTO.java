package com.esboco_comix.cupom.dto;

import lombok.Builder;

@Builder 
public record CadastrarCupomDTO (
    int idCliente,
    double valor,
    boolean isPromocional,
    boolean isTroca
){}
