package com.esboco_comix.model.entidades;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Estoque {
    private int idQuadrinho;
    private int quantidadeTotal;

    public void validarRetirada(ItemPedido item){
        if (quantidadeTotal < item.getQuantidade()) {
            throw new IllegalArgumentException(
                "Quantidade do item excede a quantidade disponível em estoque!"
            );
        }
    }  
}
