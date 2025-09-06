package com.esboco_comix.dao.impl.pedido;

import com.esboco_comix.dao.mapper.impl.PedidoPosVendaMapper;
import com.esboco_comix.dto.PedidoPosVendaDTO;
import com.esboco_comix.model.entidades.PedidoPosVenda;
import com.esboco_comix.utils.ConexaoFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PedidoPosVendaDAO {

    private final PedidoPosVendaMapper pedidoPosVendaMapper = new PedidoPosVendaMapper();

    public PedidoPosVendaDTO inserir(PedidoPosVenda p) {
        try (
            Connection connection = ConexaoFactory.getConexao();

            PreparedStatement pst = connection.prepareStatement(
                """
                INSERT INTO
                    pedidos_pos_venda (ppv_ped_id, ppv_qua_id, ppv_quantidade, ppv_status, ppv_tipo)
                VALUES
                    (?, ?, ?, ?, ?);
                """,
                    Statement.RETURN_GENERATED_KEYS
            );
        ) {
            pst.setInt(1, p.getIdPedido());
            pst.setInt(2, p.getIdQuadrinho());
            pst.setInt(3, p.getQuantidade());
            pst.setString(4, p.getStatus().name());
            pst.setString(5, p.getTipo().name());

            if (pst.executeUpdate() == 0){
                throw new IllegalStateException("Inserção de pedido pós-venda não executada!");
            }
            ResultSet rs = pst.getGeneratedKeys();
            PedidoPosVendaDTO pedido = null;
            if (rs.next()){
                pedido = consultarByID(rs.getInt(1));
            }

            return pedido;
        } catch (Exception e){
            throw new IllegalStateException(e);
        }
    }

    public List<PedidoPosVendaDTO> consultarTodos() {
        try (
            Connection connection = ConexaoFactory.getConexao();

            PreparedStatement pst = connection.prepareStatement(
                "SELECT * FROM vw_pedidos_pos_venda;",
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY
            );
        ) {

            ResultSet rs = pst.executeQuery();

            if (!rs.next()) {
                return new ArrayList<>();
            }
            rs.beforeFirst();

            List<PedidoPosVendaDTO> pedidosPosVenda = new ArrayList<>();
            while(rs.next()){
                pedidosPosVenda.add(pedidoPosVendaMapper.mapearDTO(rs));
            }

            return pedidosPosVenda;
        } catch (Exception e){
            throw new IllegalStateException(e);
        }
    }

    public PedidoPosVendaDTO consultarByID(int id) {
        try (
            Connection connection = ConexaoFactory.getConexao();

            PreparedStatement pst = connection.prepareStatement(
            "SELECT * FROM vw_pedidos_pos_venda WHERE ppv_id = ?;"
            );
        ) {
            pst.setInt(1, id);

            ResultSet rs = pst.executeQuery();

            if (!rs.next()) {
                throw new IllegalStateException("Nenhum pedido pós venda encontrado.");
            }

            return pedidoPosVendaMapper.mapearDTO(rs);
        } catch (Exception e){
            throw new IllegalStateException(e);
        }
    }

    public PedidoPosVendaDTO atualizarStatus(PedidoPosVenda p) {
        try (
            Connection conn = ConexaoFactory.getConexao();

            PreparedStatement pst = conn.prepareStatement(
                "UPDATE pedidos_pos_venda set "+
                        "ppv_status = ? WHERE ppv_ped_id = ? AND ppv_qua_id = ?;"
            );
        ) {
            pst.setString(1, p.getStatus().name());
            pst.setInt(2, p.getIdPedido());
            pst.setInt(3, p.getIdQuadrinho());

            if (pst.executeUpdate() == 0) {
                throw new IllegalStateException("Atualização não foi sucedida!");
            }

            return consultarByID(p.getId());
        } catch (Exception e){
            throw new IllegalStateException(e);
        }
    }

    public List<PedidoPosVendaDTO> consultarByIdPedido(int id) {
        try (
            Connection connection = ConexaoFactory.getConexao();

            PreparedStatement pst = connection.prepareStatement(
                "SELECT * FROM vw_pedidos_pos_venda WHERE ppv_ped_id = ?;",
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY
            );
        ) {

            pst.setInt(1, id);

            ResultSet rs = pst.executeQuery();

            if (!rs.next()) {
                return new ArrayList<>();
            }
            rs.beforeFirst();

            List<PedidoPosVendaDTO> pedidosPosVenda = new ArrayList<>();
            while(rs.next()){
                pedidosPosVenda.add(pedidoPosVendaMapper.mapearDTO(rs));
            }

            return pedidosPosVenda;
        } catch (Exception e){
            throw new IllegalStateException(e);
        }
    }

    public List<PedidoPosVendaDTO> consultarPorIDCliente(int id) {
        try (
            Connection connection = ConexaoFactory.getConexao();

            PreparedStatement pst = connection.prepareStatement(
                "SELECT * FROM vw_pedidos_pos_venda WHERE ped_cli_id = ?;",
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY
            );
        ) {

            pst.setInt(1, id);

            ResultSet rs = pst.executeQuery();

            if (!rs.next()) {
                return new ArrayList<>();
            }
            rs.beforeFirst();

            List<PedidoPosVendaDTO> pedidosPosVenda = new ArrayList<>();
            while(rs.next()){
                pedidosPosVenda.add(pedidoPosVendaMapper.mapearDTO(rs));
            }

            return pedidosPosVenda;
        } catch (Exception e){
            throw new IllegalStateException(e);
        }
    }

}
