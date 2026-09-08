package com.esboco_comix.quadrinho.dominio;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class GrupoPrecificacao {
    private int id;
    private int porcentagem;
    private String nome;
}
