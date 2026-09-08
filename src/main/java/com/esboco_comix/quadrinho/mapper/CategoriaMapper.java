package com.esboco_comix.quadrinho.mapper;

import com.esboco_comix.quadrinho.dominio.Categoria;
import com.esboco_comix.quadrinho.dto.CategoriaDTO;

public class CategoriaMapper {

    public CategoriaDTO toDTO(Categoria categoria) {
        return new CategoriaDTO(
            categoria.getNome()
        );
    }

}