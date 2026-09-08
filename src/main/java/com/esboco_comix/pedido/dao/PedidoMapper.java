package com.esboco_comix.pedido.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.esboco_comix.cliente.dominio.entidades.Endereco;
import com.esboco_comix.cliente.dominio.value_objects.Cep;
import com.esboco_comix.core.dao.ResultSetMapper;
import com.esboco_comix.pedido.dominio.Pedido;
import com.esboco_comix.pedido.dominio.enuns.StatusPedido;
import com.esboco_comix.pedido.dto.PedidoDTO;

public class PedidoMapper implements ResultSetMapper<Pedido, PedidoDTO> {

    @Override
    public Pedido mapearEntidade(ResultSet rs) throws SQLException {
        Pedido pedido = new Pedido();
        pedido.setId(rs.getInt("ped_id"));
        pedido.setIdCliente(rs.getInt("ped_cli_id"));
        pedido.setStatus(StatusPedido.valueOf(rs.getString("ped_status")));
        pedido.setValorTotal(rs.getDouble("ped_valor_total"));
        
        Endereco endereco = new Endereco();
        endereco.setId(rs.getInt("ped_end_id"));
        endereco.setFraseCurta(rs.getString("end_frase_curta"));
        endereco.setCidade(rs.getString("end_cidade"));
        endereco.setEstado(rs.getString("end_estado"));
        endereco.setPais(rs.getString("end_pais"));
        endereco.setCep(new Cep(rs.getString("end_cep")));
        pedido.setEnderecoEntrega(endereco);

        pedido.setValorFrete(rs.getDouble("ped_valor_frete"));
        pedido.setData(rs.getTimestamp("ped_data").toLocalDateTime());

        return pedido;
    }

    @Override
    public PedidoDTO mapearDTO(ResultSet rs) throws SQLException {
        PedidoDTO dto = new PedidoDTO();
        dto.setPedido(mapearEntidade(rs));
        dto.setItensPedidoDTO(new ArrayList<>());
        dto.setNomeCliente(rs.getString("cli_nome"));
        return dto;
    }

}