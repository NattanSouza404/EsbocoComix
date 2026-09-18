package com.esboco_comix.pedido.mapper;

import java.util.ArrayList;
import java.util.List;

import com.esboco_comix.carrinho.dominio.ItemCarrinho;
import com.esboco_comix.pedido.dominio.ItemPedido;

public class ItemPedidoMapper {
    public List<ItemPedido> toListaItemPedidos(List<ItemCarrinho> itensCarrinho){
        List<ItemPedido> itensPedido = new ArrayList<>();
        for (ItemCarrinho itemCarrinho : itensCarrinho) {
            itensPedido.add(
                ItemPedido.builder()
                    .idPedido(itemCarrinho.getIdPedido())
                    .idQuadrinho(itemCarrinho.getIdQuadrinho())
                    .quantidade(itemCarrinho.getQuantidade())
                    .preco(itemCarrinho.getPreco())
                .build()
            );
        }
        return itensPedido;
    }
}
