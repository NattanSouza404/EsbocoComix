package com.esboco_comix.service;

import java.util.List;

import com.esboco_comix.model.Carrinho;
import com.esboco_comix.model.ItemCarrinho;

public class CarrinhoService {

    public void adicionar(ItemCarrinho itemCarrinho, Carrinho carrinho) {
        carrinho.getItensCarrinho().add(itemCarrinho);
    }

    public void atualizarQuantidade(ItemCarrinho itemCarrinho, Carrinho carrinho) {
        List<ItemCarrinho> itens = carrinho.getItensCarrinho();

        for (int i = 0; i < itens.size(); i++) {
            if (itens.get(i).getIdQuadrinho() == itemCarrinho.getIdQuadrinho()){
                itens.set(i, itemCarrinho);
            }
        }
    }

}
