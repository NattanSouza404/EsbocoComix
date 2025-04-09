package com.esboco_comix.dto;

import com.esboco_comix.model.entidades.ItemPedido;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemCarrinhoDTO {
    private int idPedido;
    private int idQuadrinho;
    private int quantidade;

    private double preco;
    private String nome;
    private String urlImagem;

    public ItemPedido toItemPedido(){
        ItemPedido item = new ItemPedido();
        
        item.setIdPedido(idPedido);
        item.setIdQuadrinho(idQuadrinho);
        item.setQuantidade(quantidade);

        return item;
    }
}
