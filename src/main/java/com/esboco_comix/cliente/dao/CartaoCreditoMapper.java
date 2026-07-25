package com.esboco_comix.cliente.dao;

import com.esboco_comix.cliente.dominio.entidades.CartaoCredito;
import com.esboco_comix.cliente.dominio.enuns.BandeiraCartao;
import com.esboco_comix.dao.mapper.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CartaoCreditoMapper implements ResultSetMapper<CartaoCredito, CartaoCredito>{
    @Override
    public CartaoCredito mapearEntidade(ResultSet rs) throws SQLException {
        CartaoCredito c = new CartaoCredito();
        c.setId(rs.getInt("cre_id"));
        c.setNumero(rs.getString("cre_numero"));
        c.setNomeImpresso(rs.getString("cre_nome_impresso"));
        c.setCodigoSeguranca(rs.getString("cre_codigo_seguranca"));
        c.setPreferencial(rs.getBoolean("cre_is_preferencial"));
        c.setBandeiraCartao(BandeiraCartao.valueOf(rs.getString("bcc_nome")));
        c.setIdCliente(rs.getInt("cre_cli_id"));
        c.setIsAtivo(rs.getBoolean("cre_is_ativo"));
        return c;
    }

    @Override
    public CartaoCredito mapearDTO(ResultSet rs) throws SQLException {
        throw new UnsupportedOperationException("Sem implementação para mapearDTO");
    }
}
