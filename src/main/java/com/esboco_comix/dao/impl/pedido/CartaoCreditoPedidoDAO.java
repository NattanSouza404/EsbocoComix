package com.esboco_comix.dao.impl.pedido;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import com.esboco_comix.dao.IDAO;
import com.esboco_comix.model.entidades.CartaoCreditoPedido;
import com.esboco_comix.utils.ConexaoFactory;

public class CartaoCreditoPedidoDAO implements IDAO<CartaoCreditoPedido>{

    @Override
    public CartaoCreditoPedido inserir(CartaoCreditoPedido e) throws Exception {
        Connection connection = ConexaoFactory.getConexao();

        PreparedStatement pst = connection.prepareStatement(
            "INSERT INTO cartoes_credito_pedido ("+
                "ccp_cre_id, ccp_ped_id, cpp_valor)"+
                "VALUES (?, ?, ?);",
                Statement.RETURN_GENERATED_KEYS
        );

        try {
            pst.setInt(1, e.getIdCartaoCredito());
            pst.setInt(2, e.getIdPedido());
            pst.setDouble(3, e.getValor());

            if (pst.executeUpdate() == 0){
                throw new Exception("Inserção de cartão de crédito no pedido não executada!");
            }

            ResultSet rs = pst.getGeneratedKeys();
            CartaoCreditoPedido cartaoCreditoPedidoInserido = null;
            if (rs.next()){
                cartaoCreditoPedidoInserido = consultarByID(e.getIdCartaoCredito(), e.getIdPedido());
            }

            return cartaoCreditoPedidoInserido;
        } catch (Exception ex){
            throw ex;
        } finally {
            connection.close();
            pst.close();
        }
    }

    @Override
    public List<CartaoCreditoPedido> consultarTodos() throws Exception {
        throw new UnsupportedOperationException("Unimplemented method 'consultarTodos'");
    }

    public CartaoCreditoPedido consultarByIDPedido(int id) throws Exception {
        Connection connection = ConexaoFactory.getConexao();

        PreparedStatement pst = connection.prepareStatement(
            "SELECT * FROM cartoes_credito_pedido WHERE cre_id = ?;"
        );

        try {
            pst.setInt(1, id);

            ResultSet rs = pst.executeQuery();
    
            if (!rs.next()) {
                throw new Exception("Cartão de crédito no pedido não encontrado.");
            }
            return mapearEntidade(rs);  
        } catch (Exception e){
            throw e;
        } finally {
            connection.close();
            pst.close();
        }
    }

    public CartaoCreditoPedido consultarByID(int idCartaoCredito, int idPedido) throws Exception {
        Connection connection = ConexaoFactory.getConexao();

        PreparedStatement pst = connection.prepareStatement(
            "SELECT * FROM cartoes_credito_pedido WHERE ccp_cre_id = ? AND ccp_ped_id = ?;"
        );

        try {
            pst.setInt(1, idCartaoCredito);
            pst.setInt(2, idPedido);

            ResultSet rs = pst.executeQuery();
    
            if (!rs.next()){
                throw new Exception("Cartão de crédito no pedido não encontrado!");
            }
            
            return mapearEntidade(rs);
        } catch (Exception e){
            throw e;
        } finally {
            connection.close();
            pst.close();
        }

    }

    @Override
    public CartaoCreditoPedido atualizar(CartaoCreditoPedido e) throws Exception {
        throw new UnsupportedOperationException("Unimplemented method 'atualizar'");
    }

    @Override
    public void deletar(CartaoCreditoPedido e) throws Exception {
        throw new UnsupportedOperationException("Unimplemented method 'deletar'");
    }

    @Override
    public CartaoCreditoPedido consultarByID(int id) throws Exception {
        throw new UnsupportedOperationException("Unimplemented method 'consultarByID'");
    }

    private CartaoCreditoPedido mapearEntidade(ResultSet rs) throws SQLException {
        CartaoCreditoPedido ccp = new CartaoCreditoPedido();

        ccp.setIdCartaoCredito(rs.getInt("ccp_cre_id"));
        ccp.setIdCartaoCredito(rs.getInt("ccp_cre_id"));
        ccp.setIdCartaoCredito(rs.getInt("ccp_cre_id"));

        return ccp;
    }
    
}
