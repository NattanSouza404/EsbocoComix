package com.esboco_comix.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import com.esboco_comix.dao.IDAO;
import com.esboco_comix.model.entidades.ItemPedido;
import com.esboco_comix.model.entidades.Pedido;
import com.esboco_comix.utils.ConexaoFactory;

public class PedidoDAO implements IDAO<Pedido>{

    @Override
    public Pedido inserir(Pedido e) throws Exception {
        Connection connection = ConexaoFactory.getConexao();

        PreparedStatement pst = connection.prepareStatement(
            "INSERT INTO pedidos("+
                "ped_cli_id, ped_status)"+
                "VALUES (?, ?);",
                Statement.RETURN_GENERATED_KEYS
        );

        try {
            pst.setInt(1, e.getIdCliente());
            pst.setString(2, e.getStatus());

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

    @Override
    public List<Pedido> consultarTodos() throws Exception {
        throw new UnsupportedOperationException("Unimplemented method 'consultarTodos'");
    }

    @Override
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
            return mapearToResultToPedido(rs);    
        } catch (Exception e){
            throw e;
        } finally {
            connection.close();
            pst.close();
        }
    }

    @Override
    public Pedido atualizar(Pedido e) throws Exception {
        throw new UnsupportedOperationException("Unimplemented method 'atualizar'");
    }

    @Override
    public void deletar(Pedido e) throws Exception {
        throw new UnsupportedOperationException("Unimplemented method 'deletar'");
    }

    private Pedido mapearToResultToPedido(ResultSet rs) throws SQLException {
        Pedido pedido = new Pedido();
        pedido.setId(rs.getInt("ped_id"));
        pedido.setIdCliente(rs.getInt("ped_cli_id"));
        pedido.setStatus(rs.getString("ped_status"));
        return pedido;
    }
    
}
