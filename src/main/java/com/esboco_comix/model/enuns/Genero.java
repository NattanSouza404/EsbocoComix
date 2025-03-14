package com.esboco_comix.model.enuns;

import lombok.Getter;

@Getter
public enum Genero {
    MASCULINO("Masculino"),
    FEMININO("Feminino"),
    OUTRO("Outro");
    
    private String nome;
        
    Genero(String nome) {
        this.nome = nome;
    }
}
