package com.esboco_comix.dao.impl.pedido;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.esboco_comix.model.entidades.Endereco;
import com.esboco_comix.model.entidades.Pedido;
import com.esboco_comix.model.entidades.StatusPedido;
import com.esboco_comix.utils.ConexaoFactory;

public class PedidoDAO {

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

    public List<Pedido> consultarTodos() throws Exception {
        Connection conn = ConexaoFactory.getConexao();

        PreparedStatement pst = conn.prepareStatement(
            "SELECT * FROM pedidos;",
            ResultSet.TYPE_SCROLL_INSENSITIVE,
            ResultSet.CONCUR_READ_ONLY
        );

        try {
            ResultSet rs = pst.executeQuery();

            if (!rs.next()) {
                throw new Exception("Nenhum registro encontrado de pedido.");
            }
            rs.beforeFirst();

            List<Pedido> pedidos = new ArrayList<>();

            while (rs.next()){                
                pedidos.add(mapearEntidade(rs));
            }

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

    private Pedido mapearEntidade(ResultSet rs) throws SQLException {
        Pedido pedido = new Pedido();
        pedido.setId(rs.getInt("ped_id"));
        pedido.setIdCliente(rs.getInt("ped_cli_id"));
        pedido.setStatus(StatusPedido.valueOf(rs.getString("ped_status")));
        
        Endereco endereco = new Endereco();
        endereco.setId(rs.getInt("ped_end_id"));
        pedido.setEnderecoEntrega(endereco);

        pedido.setValorFrete(rs.getDouble("ped_valor_frete"));

        return pedido;
    }

    public List<Pedido> consultarByIDCliente(int idCliente) throws Exception {
        Connection conn = ConexaoFactory.getConexao();

        PreparedStatement pst = conn.prepareStatement(
            "SELECT * FROM pedidos where ped_cli_id = ?;",
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

            List<Pedido> pedidos = new ArrayList<>();

            while (rs.next()){                
                pedidos.add(mapearEntidade(rs));
            }

            return pedidos;
        }
        catch (Exception e){
            throw e;
        } finally {
            pst.close();
            conn.close();
        }
    }
    
}
