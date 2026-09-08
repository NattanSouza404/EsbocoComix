package com.esboco_comix.carrinho.service;

import com.esboco_comix.carrinho.dominio.Carrinho;
import com.esboco_comix.carrinho.dominio.ItemCarrinhoDTO;

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
