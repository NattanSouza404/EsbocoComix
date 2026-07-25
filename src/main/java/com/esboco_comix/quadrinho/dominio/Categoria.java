package com.esboco_comix.quadrinho.dominio;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Categoria {
    private int id;
    private String nome;
}
