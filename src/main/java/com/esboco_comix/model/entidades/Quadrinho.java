package com.esboco_comix.model.entidades;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Quadrinho {
    private int id;

    private LocalDate ano;
    private String titulo;
    private Double preco;
    private String autor;
    private String editora;
    private Integer edicao;
    private Integer numeroPaginas;
    private Integer altura;
    private Integer largura;
    private Integer profundidade;
    private Integer peso;
    private String codigoBarras;
    private String isbn;
    private String sinopse;
    
    @JsonProperty("isAtivo")
    private Boolean isAtivo;

    private String urlImagem;
    
    // TODO Grupo precificação
}
