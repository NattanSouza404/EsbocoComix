package com.esboco_comix.dao.impl.cartao_credito;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.esboco_comix.model.entidades.CartaoCredito;
import com.esboco_comix.model.enuns.BandeiraCartao;
import com.esboco_comix.utils.ConexaoFactory;

public class CartaoCreditoDAO {

    public CartaoCredito inserir(CartaoCredito c) throws Exception {
        Connection connection = ConexaoFactory.getConexao();

        PreparedStatement pst = connection.prepareStatement(
            """
                INSERT INTO cartoes_credito(
                cre_numero, cre_nome_impresso, cre_codigo_seguranca,
                cre_is_preferencial, cre_bcc_id, cre_cli_id)
                VALUES (
                    ?, ?, ?,
                    ?, (SELECT bcc_id FROM bandeiras_cartao_credito WHERE bcc_nome = ?), ?
                );
                """,
                Statement.RETURN_GENERATED_KEYS
        );

        try {
            pst.setString(1, c.getNumero());
            pst.setString(2, c.getNomeImpresso());
            pst.setString(3, c.getCodigoSeguranca());
            pst.setBoolean(4, c.isPreferencial());
            pst.setString(5, c.getBandeiraCartao().name());
            pst.setInt(6, c.getIdCliente());

            if (pst.executeUpdate() == 0){
                throw new Exception("Inserção de cartão de crédito não executada!");
            }
            ResultSet rs = pst.getGeneratedKeys();
            CartaoCredito cartaoCreditoInserido = null;
            if (rs.next()){
                cartaoCreditoInserido = consultarByID(rs.getInt(1));
            }

            return cartaoCreditoInserido;
        } catch (Exception ex){
            throw ex;
        } finally {
            connection.close();
            pst.close();
        }
    }

    public CartaoCredito consultarByID(int id) throws Exception {
        Connection connection = ConexaoFactory.getConexao();

        PreparedStatement pst = connection.prepareStatement(
            """
                SELECT
                    *
                FROM
                    cartoes_credito
                JOIN
                    bandeiras_cartao_credito ON bcc_id = cre_bcc_id
                WHERE cre_id = ?;
                """
        );

        try {
            pst.setInt(1, id);

            ResultSet rs = pst.executeQuery();
    
            if (!rs.next()) {
                throw new Exception("Cartão de crédito não encontrado.");
            }
            return mapearEntidade(rs);  
        } catch (Exception e){
            throw e;
        } finally {
            connection.close();
            pst.close();
        }
    }

    public CartaoCredito atualizar(CartaoCredito c) throws Exception {
        Connection conn = ConexaoFactory.getConexao(); 
    
        PreparedStatement pst = conn.prepareStatement(
            """
                UPDATE cartoes_credito SET
                    cre_numero = ?,
                    cre_nome_impresso = ?,
                    cre_codigo_seguranca = ?,
                    cre_is_preferencial = ?,
                    cre_bcc_id = (SELECT bcc_id FROM bandeiras_cartao_credito WHERE bcc_nome = ?),
                    cre_cli_id = ?
                WHERE cre_id = ?;
                """
        );
    
        try {
            pst.setString(1, c.getNumero());
            pst.setString(2, c.getNomeImpresso());
            pst.setString(3, c.getCodigoSeguranca());
            pst.setBoolean(4, c.isPreferencial());
            pst.setString(5, c.getBandeiraCartao().name());
            pst.setInt(6, c.getIdCliente());

            pst.setInt(7, c.getId());
        
            if (pst.executeUpdate() == 0) {
                throw new Exception("Atualização não foi sucedida!");
            }

            return consultarByID(c.getId());
        } catch (Exception ex){
            throw ex;
        } finally {
            pst.close();
            conn.close();
        }
    }

    public void deletar(CartaoCredito c) throws Exception {
        Connection conn = ConexaoFactory.getConexao();

        PreparedStatement pst = conn.prepareStatement(
            "DELETE FROM cartoes_credito WHERE cre_id = ?"
        );

        try {
            pst.setInt(1, c.getId());

            if (pst.executeUpdate() == 0) {
                throw new Exception("Deleção não realizada. Cartão de crédito de id " + c.getId() + " não encontrado.");
            }

        } catch (Exception ex){
            throw ex;
        } finally {
            pst.close();
            conn.close();
        }
    }

    public List<CartaoCredito> consultarByIDCliente(int idCliente) throws Exception {
        Connection connection = ConexaoFactory.getConexao();

        PreparedStatement pst = connection.prepareStatement(
            """
                SELECT
                    *
                FROM
                    cartoes_credito
                    JOIN
                    bandeiras_cartao_credito ON bcc_id = cre_bcc_id
                WHERE cre_cli_id = ?
                """,
            ResultSet.TYPE_SCROLL_INSENSITIVE,
            ResultSet.CONCUR_READ_ONLY
        );

        try {
            pst.setInt(1, idCliente);

            ResultSet rs = pst.executeQuery();
    
            if (!rs.next()) {
                throw new Exception("Esse cliente não possui cartão de crédito.");
            }
            rs.beforeFirst();
    
            List<CartaoCredito> cartoesCredito = new ArrayList<>();
            while(rs.next()){
                cartoesCredito.add(mapearEntidade(rs));
            }

            return cartoesCredito;    
        } catch (Exception e){
            throw e;
        } finally {
            connection.close();
            pst.close();
        }
    }

    private CartaoCredito mapearEntidade(ResultSet rs) throws Exception {
        CartaoCredito c = new CartaoCredito();
        c.setId(rs.getInt("cre_id"));
        c.setNumero(rs.getString("cre_numero"));
        c.setNomeImpresso(rs.getString("cre_nome_impresso"));
        c.setCodigoSeguranca(rs.getString("cre_codigo_seguranca"));
        c.setPreferencial(rs.getBoolean("cre_is_preferencial"));
        c.setBandeiraCartao(BandeiraCartao.valueOf(rs.getString("bcc_nome")));
        c.setIdCliente(rs.getInt("cre_cli_id"));
        return c;
    }

}
