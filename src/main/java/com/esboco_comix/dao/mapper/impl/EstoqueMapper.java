package com.esboco_comix.dao.mapper.impl;

import com.esboco_comix.dao.mapper.ResultSetMapper;
import com.esboco_comix.model.entidades.Estoque;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EstoqueMapper implements ResultSetMapper<Estoque, Estoque> {

    @Override
    public Estoque mapearEntidade(ResultSet rs) throws SQLException {
        Estoque e  = new Estoque();

        e.setIdQuadrinho(rs.getInt("est_qua_id"));
        e.setQuantidadeTotal(rs.getInt("est_quantidade_total"));

        return e;
    }

    @Override
    public Estoque mapearDTO(ResultSet rs) throws SQLException {
        throw new UnsupportedOperationException("Sem implementação para mapearDTO");
    }
}
