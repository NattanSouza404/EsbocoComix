package com.fatec.model.entidades;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Telefone {
    private int id;
    private String ddd;
    private String numero;
    private String tipo;
}
