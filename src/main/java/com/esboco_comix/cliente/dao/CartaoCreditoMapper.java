package com.esboco_comix.cliente.dao;

import com.esboco_comix.cliente.dominio.entidades.CartaoCredito;
import com.esboco_comix.cliente.dominio.enuns.BandeiraCartao;
import com.esboco_comix.core.dao.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CartaoCreditoMapper implements ResultSetMapper<CartaoCredito, CartaoCredito>{
    @Override
    public CartaoCredito mapearEntidade(ResultSet rs) throws SQLException {
        return CartaoCredito.builder()
            .id(rs.getInt("cre_id"))
            .numero(rs.getString("cre_numero"))
            .nomeImpresso(rs.getString("cre_nome_impresso"))
            .codigoSeguranca(rs.getString("cre_codigo_seguranca"))
            .isPreferencial(rs.getBoolean("cre_is_preferencial"))
            .bandeiraCartao(BandeiraCartao.valueOf(rs.getString("bcc_nome")))
            .idCliente(rs.getInt("cre_cli_id"))
            .isAtivo(rs.getBoolean("cre_is_ativo"))
        .build();
    }

    @Override
    public CartaoCredito mapearDTO(ResultSet rs) throws SQLException {
        throw new UnsupportedOperationException("Sem implementação para mapearDTO");
    }
}
