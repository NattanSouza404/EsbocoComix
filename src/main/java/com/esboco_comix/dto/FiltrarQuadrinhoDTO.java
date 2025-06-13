package com.esboco_comix.dto;

import java.time.LocalDate;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FiltrarQuadrinhoDTO {
    private String titulo;
    private String autor;
    private LocalDate ano;
    private Double preco;
    private String editora;
    private Integer edicao;
    private Integer numeroPaginas;
    private Integer altura;
    private Integer largura;
    private Integer profundidade;
    private Integer peso;
    private String codigoBarras;
    private String isbn;
    private String grupoPrecificacao;

    private List<String> categorias;
}
