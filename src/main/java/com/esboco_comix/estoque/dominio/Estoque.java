package com.esboco_comix.estoque.dominio;

import com.esboco_comix.model.entidades.ItemPedido;

import lombok.Getter;

@Getter
public class Estoque {
    private int idQuadrinho;
    private int quantidadeTotal;

    public Estoque(int idQuadrinho, int quantidadeTotal) {
        if (quantidadeTotal < 0) {
            throw new IllegalArgumentException("Quantidade total não pode ser negativa!");
        }

        this.idQuadrinho = idQuadrinho;
        this.quantidadeTotal = quantidadeTotal;
    }

    public void validarRetirada(ItemPedido item){
        if (quantidadeTotal < item.getQuantidade()) {
            throw new IllegalArgumentException(
                "Quantidade do item excede a quantidade disponível em estoque!"
            );
        }
    }  
}
