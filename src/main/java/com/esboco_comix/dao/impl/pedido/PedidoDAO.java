package com.esboco_comix.dao.impl.pedido;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.esboco_comix.dao.mapper.impl.ItemPedidoMapper;
import com.esboco_comix.dao.mapper.impl.PedidoMapper;
import com.esboco_comix.dto.PedidoDTO;
import com.esboco_comix.model.entidades.Pedido;
import com.esboco_comix.utils.ConexaoFactory;

public class PedidoDAO {

    private PedidoMapper pedidoMapper = new PedidoMapper();
    private ItemPedidoMapper itemPedidoMapper = new ItemPedidoMapper();

    public Pedido inserir(Pedido e) throws Exception {
        try (
            Connection connection = ConexaoFactory.getConexao();

            PreparedStatement pst = connection.prepareStatement(
                "INSERT INTO pedidos("+
                    "ped_cli_id, ped_status, ped_end_id, ped_valor_total, ped_valor_frete)"+
                    "VALUES (?, ?, ?, ?, ?);",
                    Statement.RETURN_GENERATED_KEYS
            );
        ) {
            pst.setInt(1, e.getIdCliente());
            pst.setString(2, e.getStatus().name());
            pst.setInt(3, e.getEnderecoEntrega().getId());
            pst.setDouble(4, e.getValorTotal());
            pst.setDouble(5, e.getValorFrete());

            if (pst.executeUpdate() == 0){
                throw new Exception("Inserção de pedido não executada!");
            }

            ResultSet rs = pst.getGeneratedKeys();
            Pedido pedidoInserido = null;
            if (rs.next()){
                pedidoInserido = consultarByID(rs.getInt(1));
            }

            return pedidoInserido;
        }
    }

    public List<PedidoDTO> consultarTodos() throws Exception {
        try (
            Connection conn = ConexaoFactory.getConexao();

            PreparedStatement pst = conn.prepareStatement(
            """
                SELECT * FROM vw_pedidos;
                """,
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY
            );
        ) {
            ResultSet rs = pst.executeQuery();

            if (!rs.next()) {
                return new ArrayList<>();
            }
            rs.beforeFirst();

            return mapearPedidosDTO(rs);
        }
    }

    public List<PedidoDTO> consultarByIDCliente(int idCliente) throws Exception {
        try (
            Connection conn = ConexaoFactory.getConexao();

            PreparedStatement pst = conn.prepareStatement(
            """
                SELECT * FROM vw_pedidos WHERE cli_id = ?;
                """,
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY
            );
        ) {
            pst.setInt(1, idCliente);

            ResultSet rs = pst.executeQuery();

            if (!rs.next()) {
                return new ArrayList<>();
            }
            rs.beforeFirst();

            return mapearPedidosDTO(rs);
        }
    }

    public Pedido consultarByID(int id) throws Exception {
        try (
            Connection connection = ConexaoFactory.getConexao();

            PreparedStatement pst = connection.prepareStatement(
                "SELECT * FROM vw_pedidos WHERE ped_id = ?;"
            );
        ) {
            pst.setInt(1, id);

            ResultSet rs = pst.executeQuery();
    
            if (!rs.next()) {
                throw new Exception("Pedido não encontrado.");
            }
            return pedidoMapper.mapearEntidade(rs);    
        }
    }

    public Pedido atualizarStatus(Pedido p) throws Exception {
        try (
            Connection conn = ConexaoFactory.getConexao(); 
    
            PreparedStatement pst = conn.prepareStatement(
                "UPDATE pedidos set "+
                    "ped_status = ? WHERE ped_id = ?;"
            );
        ) {
            pst.setString(1, p.getStatus().name());
            pst.setInt(2, p.getId());

            if (pst.executeUpdate() == 0) {
                throw new Exception("Atualização não foi sucedida!");
            }

            return consultarByID(p.getId());
        }
    }

    private List<PedidoDTO> mapearPedidosDTO(ResultSet rs) throws Exception {
        List<PedidoDTO> pedidos = new ArrayList<>();

        while (rs.next()){
            PedidoDTO dto = null;

            int id = rs.getInt("ped_id");
            for (PedidoDTO p: pedidos){
                if (p.getPedido().getId() == id){
                    dto = p;
                    break;
                }
            }

            if (dto == null){
                dto = pedidoMapper.mapearDTO(rs);
                pedidos.add(dto);
            }

            dto.getItensPedidoDTO().add(itemPedidoMapper.mapearDTO(rs));
        }

        return pedidos;
    }
    
}
