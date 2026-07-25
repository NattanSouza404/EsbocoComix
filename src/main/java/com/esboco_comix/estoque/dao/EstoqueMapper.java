package com.esboco_comix.estoque.dao;

import com.esboco_comix.dao.mapper.ResultSetMapper;
import com.esboco_comix.estoque.dominio.Estoque;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EstoqueMapper implements ResultSetMapper<Estoque, Estoque> {

    @Override
    public Estoque mapearEntidade(ResultSet rs) throws SQLException {
        return new Estoque(
            rs.getInt("id_quadrinho"),
            rs.getInt("quantidade_total")
        );
    }

    @Override
    public Estoque mapearDTO(ResultSet rs) throws SQLException {
        throw new UnsupportedOperationException("Sem implementação para mapearDTO");
    }
}
