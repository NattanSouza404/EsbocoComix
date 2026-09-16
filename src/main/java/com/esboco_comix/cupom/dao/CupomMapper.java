package com.esboco_comix.cupom.dao;

import com.esboco_comix.core.dao.ResultSetMapper;
import com.esboco_comix.cupom.dominio.Cupom;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CupomMapper implements ResultSetMapper<Cupom, Cupom> {

    @Override
    public Cupom mapearEntidade(ResultSet rs) throws SQLException {
        return Cupom.builder()
            .id(rs.getInt("cup_id"))
            .idCliente(rs.getInt("cup_cli_id"))
            .isAtivo(rs.getBoolean("cup_is_ativo"))
            .isPromocional(rs.getBoolean("cup_is_promocional"))
            .isTroca(rs.getBoolean("cup_is_troca"))
            .valor(rs.getInt("cup_valor"))
        .build();
    }

    @Override
    public Cupom mapearDTO(ResultSet rs) throws SQLException {
        throw new UnsupportedOperationException("Sem implementação para mapearDTO");
    }
}
