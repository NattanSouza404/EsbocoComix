package com.esboco_comix.pedido.dominio;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
public class ItemPedido {
    @Setter
    private int idPedido;
    private int idQuadrinho;
    private int quantidade;
    private double preco;

    @Builder
    private ItemPedido(
        int idPedido,
        int idQuadrinho,
        int quantidade,
        double preco
    ) {
        if (quantidade <= 0) {
            throw new IllegalArgumentException("Quantidade deve ser maior que zero!");
        }

        this.idPedido = idPedido;
        this.idQuadrinho = idQuadrinho;
        this.quantidade = quantidade;
        
        alterarPreco(preco);
    }

    public void alterarPreco(Double preco){
        if (preco == null){
            throw new IllegalArgumentException("Preço não pode ser nulo!");
        }
        
        if (preco <= 0){
            throw new IllegalArgumentException("Preço deve ser maior do que R$ 0,00!");
        }
        
        this.preco = preco;
    }

    public double calcularValor() {
        return preco * quantidade;
    }
}
