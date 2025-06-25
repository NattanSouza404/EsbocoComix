package com.esboco_comix.dao.impl.pedido;

import com.esboco_comix.dto.PedidoPosVendaDTO;
import com.esboco_comix.model.entidades.PedidoPosVenda;
import com.esboco_comix.model.enuns.StatusItemPedido;
import com.esboco_comix.model.enuns.TipoPedidoPosVenda;
import com.esboco_comix.utils.ConexaoFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PedidoPosVendaDAO {

    public PedidoPosVendaDTO inserir(PedidoPosVenda p) throws Exception {
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

        try {
            pst.setInt(1, p.getIdPedido());
            pst.setInt(2, p.getIdQuadrinho());
            pst.setInt(3, p.getQuantidade());
            pst.setString(4, p.getStatus().name());
            pst.setString(5, p.getTipo().name());

            if (pst.executeUpdate() == 0){
                throw new Exception("Inserção de pedido pós-venda não executada!");
            }
            ResultSet rs = pst.getGeneratedKeys();
            PedidoPosVendaDTO pedido = null;
            if (rs.next()){
                pedido = consultarByID(rs.getInt(1));
            }

            return pedido;
        } catch (Exception ex){
            throw ex;
        } finally {
            connection.close();
            pst.close();
        }
    }

    public List<PedidoPosVendaDTO> consultarTodos() throws Exception {
        Connection connection = ConexaoFactory.getConexao();

        PreparedStatement pst = connection.prepareStatement(
            """
                SELECT
                    pedidos_pos_venda.*,
                    cli_nome,
                    qua_titulo,
                    ped_cli_id,
                    ped_data
                FROM
                    pedidos_pos_venda
                    JOIN quadrinhos ON qua_id = ppv_qua_id
                    JOIN pedidos ON ped_id = ppv_ped_id
                    JOIN clientes ON ped_cli_id = cli_id
                """,
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY
        );

        try {

            ResultSet rs = pst.executeQuery();

            if (!rs.next()) {
                return new ArrayList<>();
            }
            rs.beforeFirst();

            List<PedidoPosVendaDTO> pedidosPosVenda = new ArrayList<>();
            while(rs.next()){
                pedidosPosVenda.add(mapearDTO(rs));
            }

            return pedidosPosVenda;
        } finally {
            connection.close();
            pst.close();
        }
    }

    public PedidoPosVendaDTO consultarByID(int id) throws Exception {
        Connection connection = ConexaoFactory.getConexao();

        PreparedStatement pst = connection.prepareStatement(
        """
            SELECT
                pedidos_pos_venda.*,
                cli_nome,
                qua_titulo,
                ped_cli_id,
                ped_data
            FROM
                pedidos_pos_venda
                JOIN quadrinhos ON qua_id = ppv_qua_id
                JOIN pedidos ON ped_id = ppv_ped_id
                JOIN clientes ON ped_cli_id = cli_id
            WHERE
                ppv_id = ?;
            """
        );

        try {
            pst.setInt(1, id);

            ResultSet rs = pst.executeQuery();

            if (!rs.next()) {
                throw new Exception("Nenhum pedido pós venda encontrado.");
            }

            return mapearDTO(rs);
        } finally {
            connection.close();
            pst.close();
        }
    }

    public PedidoPosVendaDTO atualizarStatus(PedidoPosVenda p) throws Exception {
        Connection conn = ConexaoFactory.getConexao();

        PreparedStatement pst = conn.prepareStatement(
            "UPDATE pedidos_pos_venda set "+
                    "ppv_status = ? WHERE ppv_ped_id = ? AND ppv_qua_id = ?;"
        );

        try {
            pst.setString(1, p.getStatus().name());
            pst.setInt(2, p.getIdPedido());
            pst.setInt(3, p.getIdQuadrinho());

            if (pst.executeUpdate() == 0) {
                throw new Exception("Atualização não foi sucedida!");
            }

            return consultarByID(p.getId());
        } finally {
            pst.close();
            conn.close();
        }
    }

    public List<PedidoPosVendaDTO> consultarByIdPedido(int id) throws Exception {
        Connection connection = ConexaoFactory.getConexao();

        PreparedStatement pst = connection.prepareStatement(
            """
                SELECT
                    pedidos_pos_venda.*,
                    cli_nome,
                    qua_titulo,
                    ped_cli_id,
                    ped_data
                FROM
                    pedidos_pos_venda
                    JOIN quadrinhos ON qua_id = ppv_qua_id
                    JOIN pedidos ON ped_id = ppv_ped_id
                    JOIN clientes ON ped_cli_id = cli_id
                WHERE
                    ppv_ped_id = ?;
                """,
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY
        );

        try {

            pst.setInt(1, id);

            ResultSet rs = pst.executeQuery();

            if (!rs.next()) {
                return new ArrayList<>();
            }
            rs.beforeFirst();

            List<PedidoPosVendaDTO> pedidosPosVenda = new ArrayList<>();
            while(rs.next()){
                pedidosPosVenda.add(mapearDTO(rs));
            }

            return pedidosPosVenda;
        } finally {
            connection.close();
            pst.close();
        }
    }

    public List<PedidoPosVendaDTO> consultarPorIDCliente(int id) throws Exception {
        Connection connection = ConexaoFactory.getConexao();

        PreparedStatement pst = connection.prepareStatement(
            """
                SELECT
                    pedidos_pos_venda.*,
                    cli_nome,
                    qua_titulo,
                    ped_cli_id,
                    ped_data
                FROM
                    pedidos_pos_venda
                    JOIN quadrinhos ON qua_id = ppv_qua_id
                    JOIN pedidos ON ped_id = ppv_ped_id
                    JOIN clientes ON ped_cli_id = cli_id
                WHERE
                    ped_cli_id = ?;
                """,
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY
        );

        try {

            pst.setInt(1, id);

            ResultSet rs = pst.executeQuery();

            if (!rs.next()) {
                return new ArrayList<>();
            }
            rs.beforeFirst();

            List<PedidoPosVendaDTO> pedidosPosVenda = new ArrayList<>();
            while(rs.next()){
                pedidosPosVenda.add(mapearDTO(rs));
            }

            return pedidosPosVenda;
        } finally {
            connection.close();
            pst.close();
        }
    }

    private PedidoPosVendaDTO mapearDTO(ResultSet rs) throws Exception{
        PedidoPosVendaDTO dto = new PedidoPosVendaDTO();

        dto.setPedidoPosVenda(mapearEntidade(rs));
        dto.setNomeCliente(rs.getString("cli_nome"));
        dto.setNomeQuadrinho(rs.getString("qua_titulo"));

        return dto;
    }

    private PedidoPosVenda mapearEntidade(ResultSet rs) throws Exception {
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

}
