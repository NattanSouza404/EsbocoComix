package com.esboco_comix.dao.impl.pedido;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.esboco_comix.dao.mapper.impl.ItemPedidoMapper;
import com.esboco_comix.dao.mapper.impl.PedidoMapper;
import com.esboco_comix.dto.PedidoDTO;
import com.esboco_comix.model.entidades.Pedido;
import com.esboco_comix.utils.ConexaoFactory;

public class PedidoDAO {

    private final PedidoMapper pedidoMapper = new PedidoMapper();
    private final ItemPedidoMapper itemPedidoMapper = new ItemPedidoMapper();

    public Pedido inserir(Pedido pedido) {
        try (
            Connection connection = ConexaoFactory.getConexao();

            PreparedStatement pst = connection.prepareStatement(
                "INSERT INTO pedidos("+
                    "ped_cli_id, ped_status, ped_end_id, ped_valor_total, ped_valor_frete)"+
                    "VALUES (?, ?, ?, ?, ?);",
                    Statement.RETURN_GENERATED_KEYS
            );
        ) {
            pst.setInt(1, pedido.getIdCliente());
            pst.setString(2, pedido.getStatus().name());
            pst.setInt(3, pedido.getEnderecoEntrega().getId());
            pst.setDouble(4, pedido.getValorTotal());
            pst.setDouble(5, pedido.getValorFrete());

            if (pst.executeUpdate() == 0){
                throw new IllegalStateException("Inserção de pedido não executada!");
            }

            ResultSet rs = pst.getGeneratedKeys();
            Pedido pedidoInserido = null;
            if (rs.next()){
                pedidoInserido = consultarByID(rs.getInt(1));
            }

            return pedidoInserido;
        } catch (Exception e){
            throw new IllegalStateException(e);
        }
    }

    public List<PedidoDTO> consultarTodos() {
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
        } catch (Exception e){
            throw new IllegalStateException(e);
        }
    }

    public List<PedidoDTO> consultarByIDCliente(int idCliente) {
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
        } catch (Exception e){
            throw new IllegalStateException(e);
        }
    }

    public Pedido consultarByID(int id) {
        try (
            Connection connection = ConexaoFactory.getConexao();

            PreparedStatement pst = connection.prepareStatement(
                "SELECT * FROM pedidos JOIN enderecos ON ped_end_id = end_id WHERE ped_id = ?;"
            );
        ) {
            pst.setInt(1, id);

            ResultSet rs = pst.executeQuery();

            if (!rs.next()) {
                throw new IllegalStateException("Pedido não encontrado.");
            }
            return pedidoMapper.mapearEntidade(rs);
        } catch (Exception e){
            throw new IllegalStateException(e);
        }
    }

    public Pedido atualizarStatus(Pedido p) {
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
                throw new IllegalStateException("Atualização não foi sucedida!");
            }

            return consultarByID(p.getId());
        } catch (Exception e){
            throw new IllegalStateException(e);
        }
    }

    private List<PedidoDTO> mapearPedidosDTO(ResultSet rs) throws SQLException {
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
