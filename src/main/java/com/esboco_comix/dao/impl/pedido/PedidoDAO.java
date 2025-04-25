package com.esboco_comix.dao.impl.pedido;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.esboco_comix.dto.ItemPedidoDTO;
import com.esboco_comix.dto.PedidoDTO;
import com.esboco_comix.model.entidades.Endereco;
import com.esboco_comix.model.entidades.ItemPedido;
import com.esboco_comix.model.entidades.Pedido;
import com.esboco_comix.model.enuns.StatusPedido;
import com.esboco_comix.utils.ConexaoFactory;

public class PedidoDAO {

    private ItemPedidoDAO itemPedidoDAO = new ItemPedidoDAO();

    public Pedido inserir(Pedido e) throws Exception {
        Connection connection = ConexaoFactory.getConexao();

        PreparedStatement pst = connection.prepareStatement(
            "INSERT INTO pedidos("+
                "ped_cli_id, ped_status, ped_end_id, ped_valor_frete)"+
                "VALUES (?, ?, ?, ?);",
                Statement.RETURN_GENERATED_KEYS
        );

        try {
            pst.setInt(1, e.getIdCliente());
            pst.setString(2, e.getStatus().name());
            pst.setInt(3, e.getEnderecoEntrega().getId());
            pst.setDouble(4, e.getValorFrete());

            if (pst.executeUpdate() == 0){
                throw new Exception("Inserção de pedido não executada!");
            }

            ResultSet rs = pst.getGeneratedKeys();
            Pedido pedidoInserido = null;
            if (rs.next()){
                pedidoInserido = consultarByID(rs.getInt(1));
            }

            return pedidoInserido;
        } catch (Exception ex){
            throw ex;
        } finally {
            connection.close();
            pst.close();
        }
    }

    public List<PedidoDTO> consultarTodos() throws Exception {
        Connection conn = ConexaoFactory.getConexao();

        PreparedStatement pst = conn.prepareStatement(
            """
            SELECT pedidos.*, cli_nome FROM pedidos
                JOIN clientes ON ped_cli_id = cli_id
            ORDER BY ped_data DESC;
            """,
            ResultSet.TYPE_SCROLL_INSENSITIVE,
            ResultSet.CONCUR_READ_ONLY
        );

        try {
            ResultSet rs = pst.executeQuery();

            if (!rs.next()) {
                throw new Exception("Nenhum registro encontrado de pedido.");
            }
            rs.beforeFirst();

            List<PedidoDTO> pedidos = new ArrayList<>();

            while (rs.next()){       
                pedidos.add(new PedidoDTO(
                    mapearEntidade(rs),
                    rs.getString("cli_nome"),
                    0.0, 
                    null
                ));
            }

            inserirItensPedido(
                pedidos,
                itemPedidoDAO.consultarTodos()
            );

            return pedidos;
        }
        catch (Exception e){
            throw e;
        } finally {
            pst.close();
            conn.close();
        }
    }

    public List<PedidoDTO> consultarByIDCliente(int idCliente) throws Exception {
        Connection conn = ConexaoFactory.getConexao();

        PreparedStatement pst = conn.prepareStatement(
            """
            SELECT pedidos.*, cli_nome FROM pedidos
                JOIN clientes ON ped_cli_id = cli_id
            WHERE ped_cli_id = ? 
            ORDER BY ped_data DESC;
            """,
            ResultSet.TYPE_SCROLL_INSENSITIVE,
            ResultSet.CONCUR_READ_ONLY
        );

        try {
            pst.setInt(1, idCliente);

            ResultSet rs = pst.executeQuery();

            if (!rs.next()) {
                throw new Exception("Nenhum registro encontrado de pedido.");
            }
            rs.beforeFirst();

            List<PedidoDTO> pedidos = new ArrayList<>();

            while (rs.next()){
                pedidos.add(new PedidoDTO(
                    mapearEntidade(rs),
                    rs.getString("cli_nome"),
                    0.0, null
                ));
            }

            inserirItensPedido(
                pedidos, itemPedidoDAO.consultarByIDCliente(idCliente) 
            );

            return pedidos;
        }
        catch (Exception e){
            throw e;
        } finally {
            pst.close();
            conn.close();
        }
    }

    public Pedido consultarByID(int id) throws Exception {
        Connection connection = ConexaoFactory.getConexao();

        PreparedStatement pst = connection.prepareStatement(
            "SELECT * FROM pedidos WHERE ped_id = ?"
        );

        try {
            pst.setInt(1, id);

            ResultSet rs = pst.executeQuery();
    
            if (!rs.next()) {
                throw new Exception("Pedido não encontrado.");
            }
            return mapearEntidade(rs);    
        } catch (Exception e){
            throw e;
        } finally {
            connection.close();
            pst.close();
        }
    }

    public Pedido atualizarStatus(Pedido p) throws Exception {
        Connection conn = ConexaoFactory.getConexao(); 
    
        PreparedStatement pst = conn.prepareStatement(
            "UPDATE pedidos set "+
                "ped_status = ? WHERE ped_id = ?;"
        );
    
        try {
            pst.setString(1, p.getStatus().name());
            pst.setInt(2, p.getId());

            if (pst.executeUpdate() == 0) {
                throw new Exception("Atualização não foi sucedida!");
            }

            return consultarByID(p.getId());
        } catch (Exception e){
            throw e;
        } finally {
            pst.close();
            conn.close();
        } 
    }

    private Pedido mapearEntidade(ResultSet rs) throws SQLException {
        Pedido pedido = new Pedido();
        pedido.setId(rs.getInt("ped_id"));
        pedido.setIdCliente(rs.getInt("ped_cli_id"));
        pedido.setStatus(StatusPedido.valueOf(rs.getString("ped_status")));
        
        Endereco endereco = new Endereco();
        endereco.setId(rs.getInt("ped_end_id"));
        pedido.setEnderecoEntrega(endereco);

        pedido.setValorFrete(rs.getDouble("ped_valor_frete"));
        pedido.setData(rs.getTimestamp("ped_data").toLocalDateTime());

        return pedido;
    }

    private void inserirItensPedido(List<PedidoDTO> pedidos, Map<Integer, List<ItemPedidoDTO>> itensMap) throws Exception {
        for (PedidoDTO p : pedidos) {
            List<ItemPedidoDTO> itens = itensMap.get(p.getPedido().getId());

            List<ItemPedido> itensPedidos = new ArrayList<>();
            for (ItemPedidoDTO i : itens) {
                itensPedidos.add(
                    i.getItemPedido()
                );
            }

            p.getPedido().setItensPedido(itensPedidos);
            p.setItensPedidoDTO(itens);
        }
    }
    
}
