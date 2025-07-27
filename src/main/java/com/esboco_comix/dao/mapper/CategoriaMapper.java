package com.esboco_comix.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.esboco_comix.model.entidades.Categoria;

public class CategoriaMapper implements ResultSetMapper<Categoria, Categoria>{

    @Override
    public Categoria mapearEntidade(ResultSet rs) throws SQLException {
        Categoria categoria = new Categoria();
        categoria.setId(rs.getInt("cat_id"));
        categoria.setNome(rs.getString("cat_nome"));

        return categoria;
    }

    @Override
    public Categoria mapearDTO(ResultSet rs) throws SQLException {
        throw new UnsupportedOperationException("Unimplemented method 'mapearDTO'");
    }

}
