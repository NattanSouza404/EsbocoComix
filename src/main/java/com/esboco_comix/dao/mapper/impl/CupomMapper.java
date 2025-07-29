package com.esboco_comix.dao.mapper.impl;

import com.esboco_comix.dao.mapper.ResultSetMapper;
import com.esboco_comix.model.entidades.Cupom;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CupomMapper implements ResultSetMapper<Cupom, Cupom> {

    @Override
    public Cupom mapearEntidade(ResultSet rs) throws SQLException {
        Cupom cupom = new Cupom();

        cupom.setId(rs.getInt("cup_id"));
        cupom.setIdCliente(rs.getInt("cup_cli_id"));
        cupom.setAtivo(rs.getBoolean("cup_is_ativo"));
        cupom.setPromocional(rs.getBoolean("cup_is_promocional"));
        cupom.setTroca(rs.getBoolean("cup_is_troca"));
        cupom.setValor(rs.getInt("cup_valor"));

        return cupom;
    }

    @Override
    public Cupom mapearDTO(ResultSet rs) throws SQLException {
        throw new UnsupportedOperationException("Sem implementação para mapearDTO");
    }
}
