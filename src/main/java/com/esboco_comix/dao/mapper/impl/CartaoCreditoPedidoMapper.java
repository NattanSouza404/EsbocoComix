package com.esboco_comix.dao.mapper.impl;

import com.esboco_comix.dao.mapper.ResultSetMapper;
import com.esboco_comix.model.entidades.CartaoCreditoPedido;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CartaoCreditoPedidoMapper implements ResultSetMapper<CartaoCreditoPedido, CartaoCreditoPedido> {

    @Override
    public CartaoCreditoPedido mapearEntidade(ResultSet rs) throws SQLException {
        CartaoCreditoPedido ccp = new CartaoCreditoPedido();

        ccp.setIdCartaoCredito(rs.getInt("ccp_cre_id"));
        ccp.setIdPedido(rs.getInt("ccp_ped_id"));
        ccp.setValor(rs.getDouble("ccp_valor"));

        return ccp;
    }

    @Override
    public CartaoCreditoPedido mapearDTO(ResultSet rs) throws SQLException {
        throw new UnsupportedOperationException("Sem implementação para mapearDTO");
    }
}
