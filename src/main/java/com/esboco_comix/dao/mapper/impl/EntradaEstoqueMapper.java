package com.esboco_comix.dao.mapper.impl;

import com.esboco_comix.dao.mapper.ResultSetMapper;
import com.esboco_comix.dto.EntradaEstoqueDTO;
import com.esboco_comix.model.entidades.EntradaEstoque;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EntradaEstoqueMapper implements ResultSetMapper<EntradaEstoque, EntradaEstoqueDTO> {

    @Override
    public EntradaEstoque mapearEntidade(ResultSet rs) throws SQLException {
        EntradaEstoque e = new EntradaEstoque();

        e.setId(rs.getInt("ees_id"));
        e.setFornecedor(rs.getString("ees_fornecedor"));
        e.setIdQuadrinho(rs.getInt("ees_qua_id"));
        e.setQuantidade(rs.getInt("ees_quantidade"));
        e.setValorCusto(rs.getDouble("ees_valor_custo"));
        e.setDataEntrada(rs.getTimestamp("ees_dt_entrada").toLocalDateTime());

        return e;
    }

    @Override
    public EntradaEstoqueDTO mapearDTO(ResultSet rs) throws SQLException {
        EntradaEstoqueDTO dto = new EntradaEstoqueDTO();

        dto.setNomeQuadrinho(rs.getString("qua_titulo"));

        dto.setEntradaEstoque(mapearEntidade(rs));

        return dto;
    }
}
