package com.esboco_comix.service.impl;

import com.esboco_comix.dto.ItemCarrinhoDTO;
import com.esboco_comix.model.Carrinho;

public class CarrinhoService {

    public Carrinho adicionar(Carrinho carrinho, ItemCarrinhoDTO itemCarrinho) throws Exception {
        // TODO: adicionar verificação do estoque

        carrinho.adicionar(itemCarrinho);

        return carrinho;
    }

    public Carrinho atualizarQuantidade(Carrinho carrinho, ItemCarrinhoDTO itemCarrinho) throws Exception {
        carrinho.atualizarQuantidade(itemCarrinho);

        return carrinho;
    }

    public void deletar(Carrinho carrinho, ItemCarrinhoDTO itemCarrinho) throws Exception {
        carrinho.deletar(itemCarrinho);
    }

}
