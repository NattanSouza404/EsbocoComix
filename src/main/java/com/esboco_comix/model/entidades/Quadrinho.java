package com.esboco_comix.model.entidades;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class Quadrinho {
    private int id;

    private LocalDate ano;
    private String titulo;
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

    private List<Categoria> categorias;

    private GrupoPrecificacao grupoPrecificacao;
}
