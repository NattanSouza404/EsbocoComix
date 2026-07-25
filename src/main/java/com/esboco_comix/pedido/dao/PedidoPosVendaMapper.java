package com.esboco_comix.pedido.dao;

import com.esboco_comix.core.dao.mapper.ResultSetMapper;
import com.esboco_comix.pedido.dominio.PedidoPosVenda;
import com.esboco_comix.pedido.dominio.enuns.StatusItemPedido;
import com.esboco_comix.pedido.dominio.enuns.TipoPedidoPosVenda;
import com.esboco_comix.pedido.dto.PedidoPosVendaDTO;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PedidoPosVendaMapper implements ResultSetMapper<PedidoPosVenda, PedidoPosVendaDTO> {
    @Override
    public PedidoPosVenda mapearEntidade(ResultSet rs) throws SQLException {
        PedidoPosVenda pedidoPosVenda = new PedidoPosVenda();

        pedidoPosVenda.setId(rs.getInt("ppv_id"));
        pedidoPosVenda.setTipo(TipoPedidoPosVenda.valueOf(rs.getString("ppv_tipo")));
        pedidoPosVenda.setQuantidade(rs.getInt("ppv_quantidade"));
        pedidoPosVenda.setStatus(StatusItemPedido.valueOf(rs.getString("ppv_status")));
        pedidoPosVenda.setIdPedido(rs.getInt("ppv_ped_id"));
        pedidoPosVenda.setIdQuadrinho(rs.getInt("ppv_qua_id"));
        pedidoPosVenda.setData(rs.getTimestamp("ppv_data").toLocalDateTime());

        return pedidoPosVenda;
    }

    @Override
    public PedidoPosVendaDTO mapearDTO(ResultSet rs) throws SQLException {
        PedidoPosVendaDTO dto = new PedidoPosVendaDTO();

        dto.setPedidoPosVenda(mapearEntidade(rs));
        dto.setNomeCliente(rs.getString("cli_nome"));
        dto.setNomeQuadrinho(rs.getString("qua_titulo"));

        return dto;
    }
}
