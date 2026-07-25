package com.esboco_comix.mapper;

import com.esboco_comix.dto.CategoriaDTO;
import com.esboco_comix.model.entidades.Categoria;

public class CategoriaMapper {

    public CategoriaDTO toDTO(Categoria categoria) {
        return new CategoriaDTO(
            categoria.getNome()
        );
    }

}