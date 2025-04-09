package com.esboco_comix.model.entidades;

import com.esboco_comix.model.enuns.TipoTelefone;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Telefone {
    private String ddd;
    private String numero;
    private TipoTelefone tipo;
}
