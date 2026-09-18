package com.esboco_comix.carrinho.dominio;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemCarrinho {
    private int idPedido;
    private int idQuadrinho;
    private int quantidade;

    private double preco;
    private String nome;
    private String urlImagem;

    @Builder
    public ItemCarrinho(
        int idPedido,
        int idQuadrinho,
        int quantidade,

        double preco,
        String nome,
        String urlImagem
    ){
        if (quantidade < 1){
            throw new IllegalArgumentException("Item do carrinho deve ter quantidade maior que 0!");
        }
        
        this.quantidade = quantidade;
        this.idPedido = idPedido;
        this.idQuadrinho = idQuadrinho;
        this.preco = preco;
        this.nome = nome;
        this.urlImagem = urlImagem;
    }

    public void somarQuantidade(int quantidade) {
        this.quantidade += quantidade;
    }
}
