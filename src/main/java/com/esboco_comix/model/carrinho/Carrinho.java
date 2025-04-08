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

    public void adicionar(ItemPedido itemCarrinho) {
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

    public void atualizarQuantidade(ItemPedido item) {
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
