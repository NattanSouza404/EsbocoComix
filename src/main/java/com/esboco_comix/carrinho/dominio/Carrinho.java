package com.esboco_comix.carrinho.dominio;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Carrinho {
    private List<ItemCarrinho> itensCarrinho = new ArrayList<>();

    public void adicionar(ItemCarrinho item) {
        for (ItemCarrinho i : itensCarrinho) {
            if (i.getIdQuadrinho() == item.getIdQuadrinho()){
                i.somarQuantidade(item.getQuantidade());
                return;
            }
        }

        itensCarrinho.add(item);
    }

    public void atualizarQuantidade(ItemCarrinho item) {
        for (ItemCarrinho i: itensCarrinho) {
            if (i.getIdQuadrinho() == item.getIdQuadrinho()){
                i.setQuantidade(item.getQuantidade());
                return;
            }
        }
    }

    public void deletar(ItemCarrinho item) {
        for (ItemCarrinho i : itensCarrinho) {
            if (i.getIdQuadrinho() == item.getIdQuadrinho()){
                itensCarrinho.remove(i);
                return;
            }
        }
    }

    public void esvaziar() {
        itensCarrinho.clear();
    }

    public boolean isVazio() {
        return itensCarrinho.isEmpty();
    }

}
