package com.esboco_comix.model.enuns;

import lombok.Getter;

@Getter
public enum TipoTelefone {
    FIXO("Fixo"),
    COMERCIAL("Comercial"),
    CELULAR("Celular"),
    VOIP("VoIP"),
    FAX("Fax");
    
    private String nome;
    
    TipoTelefone(String nome) {
        this.nome = nome;
    }
}
