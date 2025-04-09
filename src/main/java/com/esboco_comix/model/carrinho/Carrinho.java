package com.esboco_comix.model.carrinho;

import java.util.ArrayList;
import java.util.List;

import com.esboco_comix.model.entidades.ItemPedido;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Carrinho {
    private List<ItemPedido> itensCarrinho = new ArrayList<>();

    public void adicionar(ItemPedido itemCarrinho) throws Exception {
        if (itemCarrinho.getQuantidade() < 1){
            throw new Exception("Item do carrinho deve ter quantidade maior que 0!");
        }

        itensCarrinho.add((itemCarrinho));
    }

    public void deletar(ItemPedido itemCarrinho){
        for (ItemPedido item : itensCarrinho) {
            if (item.getIdQuadrinho() == itemCarrinho.getIdQuadrinho()){
                itensCarrinho.remove(item);
                return;
            }
        }
    }

    public void atualizarQuantidade(ItemPedido item) throws Exception {
        if (item.getQuantidade() < 1){
            throw new Exception("Item do carrinho deve ter quantidade maior que 0!");
        }

        for (int i = 0; i < itensCarrinho.size(); i++) {
            if (itensCarrinho.get(i).getIdQuadrinho() == item.getIdQuadrinho()){
                itensCarrinho.get(i).setQuantidade(item.getQuantidade());
            }
        }
    }

    public void esvaziar() {
        itensCarrinho.clear();
    }

}
