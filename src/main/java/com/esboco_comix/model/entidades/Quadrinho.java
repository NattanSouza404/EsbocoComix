package com.esboco_comix.model.entidades;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Quadrinho {
    private int id;
    private String urlImagem;
    private String titulo;
    private Double preco;
    private String autor;
}
