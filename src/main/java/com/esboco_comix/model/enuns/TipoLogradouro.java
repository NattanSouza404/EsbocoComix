package com.esboco_comix.model.enuns;

import lombok.Getter;

@Getter
public enum TipoLogradouro {
    
    RUA("Rua"),
    Avenida("Avenida"),
    PRACA("Praça"),
    TRAVESSA("Travessa"),
    ESTRADA("Estrada"),
    ALAMEDA("Alameda"),
    VIA("Via"),
    RODOVIA("Rodovia"),
    VIADUTO("Viaduto"),
    LARGO("Largo"),
    BECO("Beco"),
    TUNEL("Túnel");

    private String nome;

    TipoLogradouro(String nome) {
        this.nome = nome;
    }

}
