package com.esboco_comix.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.esboco_comix.dao.IDAO;
import com.esboco_comix.model.entidades.ItemPedido;
import com.esboco_comix.utils.ConexaoFactory;

public class ItemPedidoDAO implements IDAO<ItemPedido> {

    @Override
    public ItemPedido inserir(ItemPedido e) throws Exception {
        Connection connection = ConexaoFactory.getConexao();

        PreparedStatement pst = connection.prepareStatement(
            "INSERT INTO itens_pedido("+
                "ite_ped_id, ite_qua_id, ite_quantidade)"+
                "VALUES (?, ?, ?);",
                Statement.RETURN_GENERATED_KEYS
        );

        try {
            pst.setInt(1, e.getIdPedido());
            pst.setInt(2, e.getIdQuadrinho());
            pst.setInt(3, e.getQuantidade());

            if (pst.executeUpdate() == 0){
                throw new Exception("Inserção de item de pedido não executada!");
            }

            return consultarByID(e.getIdPedido(), e.getIdQuadrinho());
        } catch (Exception ex){
            throw ex;
        } finally {
            connection.close();
            pst.close();
        }
    }

    @Override
    public List<ItemPedido> consultarTodos() throws Exception {
        throw new UnsupportedOperationException("Unimplemented method 'consultarTodos'");
    }

    @Override
    public ItemPedido consultarByID(int id) throws Exception {
        throw new UnsupportedOperationException("Unimplemented method 'consultarByID'");
    }

    public ItemPedido consultarByID(int idPedido, int idQuadrinho) throws Exception {
        Connection connection = ConexaoFactory.getConexao();

        PreparedStatement pst = connection.prepareStatement(
            "SELECT * FROM itens_pedido WHERE ite_ped_id = ? AND ite_qua_id = ?;"
        );

        try {
            pst.setInt(1, idPedido);
            pst.setInt(2, idQuadrinho);

            ResultSet rs = pst.executeQuery();
    
            if (!rs.next()){
                throw new Exception("Item do pedido não encontrado!");
            }
            
            return mapearEntidade(rs);
        } catch (Exception e){
            throw e;
        } finally {
            connection.close();
            pst.close();
        }

    }

    public List<ItemPedido> consultarByIDPedido(int idPedido) throws Exception {
        Connection connection = ConexaoFactory.getConexao();

        PreparedStatement pst = connection.prepareStatement(
            "SELECT * FROM itens_pedido WHERE ite_ped_id = ?",
            ResultSet.TYPE_SCROLL_INSENSITIVE,
            ResultSet.CONCUR_READ_ONLY
        );

        try {
            pst.setInt(1, idPedido);

            ResultSet rs = pst.executeQuery();
    
            if (!rs.next()) {
                throw new Exception("Pedido não possui nenhum item.");
            }
            rs.beforeFirst();
    
            List<ItemPedido> itens = new ArrayList<>();
            while(rs.next()){
                itens.add(mapearEntidade(rs));
            }

            return itens;    
        } catch (Exception e){
            throw e;
        } finally {
            connection.close();
            pst.close();
        }
    }

    @Override
    public ItemPedido atualizar(ItemPedido e) throws Exception {
        throw new UnsupportedOperationException("Unimplemented method 'atualizar'");
    }

    @Override
    public void deletar(ItemPedido e) throws Exception {
        throw new UnsupportedOperationException("Unimplemented method 'deletar'");
    }

    private ItemPedido mapearEntidade(ResultSet rs) throws SQLException {
        ItemPedido item = new ItemPedido();  
        item.setIdPedido(rs.getInt("ite_ped_id"));
        item.setIdQuadrinho(rs.getInt("ite_qua_id"));
        item.setQuantidade(rs.getInt("ite_quantidade"));
        return item;
    }

}
