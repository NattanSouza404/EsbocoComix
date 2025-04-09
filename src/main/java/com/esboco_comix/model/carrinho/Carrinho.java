package com.esboco_comix.model.carrinho;

import java.util.ArrayList;
import java.util.List;

import com.esboco_comix.dto.ItemCarrinhoDTO;
import com.esboco_comix.model.entidades.ItemPedido;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Carrinho {
    private List<ItemCarrinhoDTO> itensCarrinho = new ArrayList<>();

    public void adicionar(ItemCarrinhoDTO itemCarrinho) throws Exception {
        if (itemCarrinho.getQuantidade() < 1){
            throw new Exception("Item do carrinho deve ter quantidade maior que 0!");
        }

        itensCarrinho.add((itemCarrinho));
    }

    public void deletar(ItemCarrinhoDTO itemCarrinho){
        for (ItemCarrinhoDTO item : itensCarrinho) {
            if (item.getIdQuadrinho() == itemCarrinho.getIdQuadrinho()){
                itensCarrinho.remove(item);
                return;
            }
        }
    }

    public void atualizarQuantidade(ItemCarrinhoDTO item) throws Exception {
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

    public List<ItemPedido> getItensPedido(){
        List<ItemPedido> itensPedido = new ArrayList<>();
        for (ItemCarrinhoDTO itemCarrinhoDTO : itensCarrinho) {
            itensPedido.add(itemCarrinhoDTO.toItemPedido());
        }
        return itensPedido;
    }

}
