package com.esboco_comix.dao.mapper.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.esboco_comix.dao.mapper.ResultSetMapper;
import com.esboco_comix.dto.ItemPedidoDTO;
import com.esboco_comix.model.entidades.ItemPedido;

public class ItemPedidoMapper implements ResultSetMapper<ItemPedido, ItemPedidoDTO> {
    
    @Override
    public ItemPedido mapearEntidade(ResultSet rs) throws SQLException {
        ItemPedido item = new ItemPedido();  
        item.setIdPedido(rs.getInt("ite_ped_id"));
        item.setIdQuadrinho(rs.getInt("ite_qua_id"));
        item.setQuantidade(rs.getInt("ite_quantidade"));
        item.setPreco(rs.getDouble("ite_valor_unitario"));
        return item;
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




