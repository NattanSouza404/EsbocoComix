package com.esboco_comix.quadrinho.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.esboco_comix.dao.mapper.ResultSetMapper;
import com.esboco_comix.quadrinho.dominio.Categoria;

public class CategoriaMapper implements ResultSetMapper<Categoria, Categoria> {

    @Override
    public Categoria mapearEntidade(ResultSet rs) throws SQLException {
        return Categoria.builder()
            .id(rs.getInt("cat_id"))
            .nome(rs.getString("cat_nome"))
        .build();
    }

    @Override
    public Categoria mapearDTO(ResultSet rs) throws SQLException {
        throw new UnsupportedOperationException("Unimplemented method 'mapearDTO'");
    }

}
