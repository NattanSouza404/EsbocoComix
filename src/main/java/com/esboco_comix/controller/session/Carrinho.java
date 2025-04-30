package com.esboco_comix.controller.session;

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
        for (ItemCarrinhoDTO item : itensCarrinho) {
            if (item.getIdQuadrinho() == itemCarrinho.getIdQuadrinho()){
                item.setQuantidade(item.getQuantidade() + itemCarrinho.getQuantidade());
                return;
            }
        }

        itensCarrinho.add(itemCarrinho);
    }

    public void deletar(ItemCarrinhoDTO itemCarrinho) throws Exception {
        for (ItemCarrinhoDTO item : itensCarrinho) {
            if (item.getIdQuadrinho() == itemCarrinho.getIdQuadrinho()){
                itensCarrinho.remove(item);
                return;
            }
        }
    }

    public void atualizarQuantidade(ItemCarrinhoDTO item) throws Exception {
        for (int i = 0; i < itensCarrinho.size(); i++) {
            if (itensCarrinho.get(i).getIdQuadrinho() == item.getIdQuadrinho()){
                itensCarrinho.get(i).setQuantidade(item.getQuantidade());
                return;
            }
        }
    }

    public List<ItemPedido> esvaziar() {
        List<ItemPedido> itensPedido = getItensPedido();
        itensCarrinho.clear();
        return itensPedido;
    }

    public boolean isVazio() {
        return itensCarrinho.isEmpty();
    }

    public List<ItemPedido> getItensPedido(){
        List<ItemPedido> itensPedido = new ArrayList<>();
        for (ItemCarrinhoDTO itemCarrinhoDTO : itensCarrinho) {
            itensPedido.add(itemCarrinhoDTO.toItemPedido());
        }
        return itensPedido;
    }

}
