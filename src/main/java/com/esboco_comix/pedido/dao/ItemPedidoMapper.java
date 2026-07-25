package com.esboco_comix.pedido.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.esboco_comix.core.dao.ResultSetMapper;
import com.esboco_comix.pedido.dominio.ItemPedido;
import com.esboco_comix.pedido.dto.ItemPedidoDTO;

public class ItemPedidoMapper implements ResultSetMapper<ItemPedido, ItemPedidoDTO> {
    
    @Override
    public ItemPedido mapearEntidade(ResultSet rs) throws SQLException {
        return ItemPedido.builder()
            .idPedido(rs.getInt("ite_ped_id"))
            .idQuadrinho(rs.getInt("ite_qua_id"))
            .quantidade(rs.getInt("ite_quantidade"))
            .preco(rs.getDouble("ite_valor_unitario"))
        .build();
    }

    @Override
    public ItemPedidoDTO mapearDTO(ResultSet rs) throws SQLException {
        ItemPedidoDTO dto = new ItemPedidoDTO();

        dto.setItemPedido(mapearEntidade(rs));
        dto.setNomeCliente(rs.getString("cli_nome"));
        dto.setNomeQuadrinho(rs.getString("qua_titulo"));
        dto.setUrlImagem(rs.getString("qua_url_imagem"));

        return dto;
    }
    
}




