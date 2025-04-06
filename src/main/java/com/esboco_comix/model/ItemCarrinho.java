package com.esboco_comix.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemCarrinho {
    private int idQuadrinho;
    
    private String nome;
    private int quantidade;
    private double preco;
}
