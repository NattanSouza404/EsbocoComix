package com.esboco_comix.carrinho.dto;

import lombok.Builder;

@Builder 
public record ItemCarrinhoDTO(
    int idPedido,
    int idQuadrinho,
    int quantidade,

    double preco,
    String nome,
    String urlImagem
) {}
