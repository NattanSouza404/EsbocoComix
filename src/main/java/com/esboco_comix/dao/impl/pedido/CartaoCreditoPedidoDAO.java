package com.esboco_comix.dao.impl.pedido;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.esboco_comix.dao.mapper.impl.CartaoCreditoPedidoMapper;
import com.esboco_comix.model.entidades.CartaoCreditoPedido;
import com.esboco_comix.utils.ConexaoFactory;

public class CartaoCreditoPedidoDAO {

    private final CartaoCreditoPedidoMapper cartaoCreditoPedidoMapper = new CartaoCreditoPedidoMapper();

    public CartaoCreditoPedido inserir(CartaoCreditoPedido cartao) {
        try (
            Connection connection = ConexaoFactory.getConexao();

            PreparedStatement pst = connection.prepareStatement(
                "INSERT INTO cartoes_credito_pedido ("+
                    "ccp_cre_id, ccp_ped_id, cpp_valor)"+
                    "VALUES (?, ?, ?);",
                    Statement.RETURN_GENERATED_KEYS
            );
        ){
            pst.setInt(1, cartao.getIdCartaoCredito());
            pst.setInt(2, cartao.getIdPedido());
            pst.setDouble(3, cartao.getValor());

            if (pst.executeUpdate() == 0){
                throw new IllegalStateException("Inserção de cartão de crédito no pedido não executada!");
            }

            ResultSet rs = pst.getGeneratedKeys();
            CartaoCreditoPedido cartaoCreditoPedidoInserido = null;
            if (rs.next()){
                cartaoCreditoPedidoInserido = consultarByID(cartao.getIdCartaoCredito(), cartao.getIdPedido());
            }

            return cartaoCreditoPedidoInserido;
        } catch (Exception e){
            throw new IllegalStateException(e);
        }
    }

    public CartaoCreditoPedido consultarByID(int idCartaoCredito, int idPedido) {
        try (
            Connection connection = ConexaoFactory.getConexao();

            PreparedStatement pst = connection.prepareStatement(
                "SELECT * FROM cartoes_credito_pedido WHERE ccp_cre_id = ? AND ccp_ped_id = ?;"
            );
        ) {
            pst.setInt(1, idCartaoCredito);
            pst.setInt(2, idPedido);

            ResultSet rs = pst.executeQuery();
    
            if (!rs.next()){
                throw new IllegalStateException("Cartão de crédito no pedido não encontrado!");
            }
            
            return cartaoCreditoPedidoMapper.mapearEntidade(rs);
        } catch (Exception e){
            throw new IllegalStateException(e);
        }
    }
    
}
