package com.esboco_comix.model.carrinho;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Carrinho {
    private List<ItemCarrinho> itensCarrinho = new ArrayList<>();

    public void adicionar(ItemCarrinho itemCarrinho) {
        itensCarrinho.add((itemCarrinho));
    }

    public void deletar(ItemCarrinho itemCarrinho){
        for (ItemCarrinho item : itensCarrinho) {
            if (item.getIdQuadrinho() == itemCarrinho.getIdQuadrinho()){
                itensCarrinho.remove(item);
                return;
            }
        }
    }

    public void atualizarQuantidade(ItemCarrinho item) {
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
