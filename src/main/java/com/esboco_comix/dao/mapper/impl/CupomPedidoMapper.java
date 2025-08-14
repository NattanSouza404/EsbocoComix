package com.esboco_comix.dao.mapper.impl;

import com.esboco_comix.dao.mapper.ResultSetMapper;
import com.esboco_comix.model.entidades.CupomPedido;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CupomPedidoMapper implements ResultSetMapper<CupomPedido, CupomPedido> {
    @Override
    public CupomPedido mapearEntidade(ResultSet rs) throws SQLException {
        CupomPedido cupomPedido = new CupomPedido();

        cupomPedido.setIdCupom(rs.getInt("cpe_cup_id"));
        cupomPedido.setIdPedido(rs.getInt("cpe_ped_id"));

        return cupomPedido;
    }

    @Override
    public CupomPedido mapearDTO(ResultSet rs) throws SQLException {
        throw new UnsupportedOperationException("Sem implementação para mapearDTO");
    }
}
