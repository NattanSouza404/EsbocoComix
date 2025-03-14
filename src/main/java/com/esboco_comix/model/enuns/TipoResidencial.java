package com.esboco_comix.model.enuns;

import lombok.Getter;

@Getter
public enum TipoResidencial {
    CASA("Casa"),
    APARTAMENTO("Apartamento"),
    CONDOMINIO("Condomínio");

    private String nome;
    
    TipoResidencial(String nome) {
        this.nome = nome;
    }

}
