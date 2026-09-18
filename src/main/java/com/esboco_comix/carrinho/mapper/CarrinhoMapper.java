package com.esboco_comix.carrinho.mapper;

import com.esboco_comix.carrinho.dominio.ItemCarrinho;
import com.esboco_comix.carrinho.dto.ItemCarrinhoDTO;

public class CarrinhoMapper {
    public ItemCarrinho toItemCarrinho(ItemCarrinhoDTO dto){
        return ItemCarrinho.builder()
            .idPedido(dto.idPedido())
            .idQuadrinho(dto.idQuadrinho())
            .nome(dto.nome())
            .preco(dto.preco())
            .quantidade(dto.quantidade())
            .urlImagem(dto.urlImagem())
        .build();
    }
}
