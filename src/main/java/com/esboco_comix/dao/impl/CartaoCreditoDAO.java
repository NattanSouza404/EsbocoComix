package com.esboco_comix.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.esboco_comix.dao.IDAO;
import com.esboco_comix.model.entidades.CartaoCredito;
import com.esboco_comix.utils.ConexaoFactory;

public class CartaoCreditoDAO implements IDAO<CartaoCredito> {

    private BandeiraCartaoDAO bandeiraCartaoDAO = new BandeiraCartaoDAO();

    @Override
    public CartaoCredito inserir(CartaoCredito c) throws Exception {
        Connection connection = ConexaoFactory.getConexao();

        PreparedStatement pst = connection.prepareStatement(
            "INSERT INTO cartoes_credito("+
                "cre_numero, cre_nome_impresso, cre_codigo_seguranca, "+
                "cre_is_preferencial, cre_bcc_id, cre_cli_id) "+
                "VALUES (?, ?, ?, ?, ?, ?);",
                Statement.RETURN_GENERATED_KEYS
        );

        try {
            pst.setString(1, c.getNumero());
            pst.setString(2, c.getNomeImpresso());
            pst.setString(3, c.getCodigoSeguranca());
            pst.setBoolean(4, c.isPreferencial());
            pst.setInt(5, c.getBandeiraCartao().getId());
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

    @Override
    public List<CartaoCredito> consultarTodos() throws Exception {
        throw new UnsupportedOperationException("Unimplemented method 'consultarTodos'");
    }

    @Override
    public CartaoCredito consultarByID(int id) throws Exception {
        Connection connection = ConexaoFactory.getConexao();

        PreparedStatement pst = connection.prepareStatement(
            "SELECT * FROM cartoes_credito WHERE cre_id = ?;"
        );

        try {
            pst.setInt(1, id);

            ResultSet rs = pst.executeQuery();
    
            if (!rs.next()) {
                throw new Exception("Cartão de crédito não encontrado.");
            }

            CartaoCredito c = new CartaoCredito();
            c.setId(rs.getInt("cre_id"));
            c.setNomeImpresso(rs.getString("cre_nome_impresso"));
            c.setPreferencial(rs.getBoolean("cre_is_preferencial"));
            c.setIdBandeiraCartao(rs.getInt("cre_bcc_id"));
            c.setIdCliente(rs.getInt("cre_cli_id"));

            return c;    
        } catch (Exception e){
            throw e;
        } finally {
            connection.close();
            pst.close();
        }
    }

    @Override
    public CartaoCredito atualizar(CartaoCredito c) throws Exception {
        throw new UnsupportedOperationException("Unimplemented method 'atualizar'");
    }

    @Override
    public void deletar(CartaoCredito c) throws Exception {
        throw new UnsupportedOperationException("Unimplemented method 'deletar'");
    }

    public List<CartaoCredito> consultarByIDCliente(int idCliente) throws Exception {
        Connection connection = ConexaoFactory.getConexao();

        PreparedStatement pst = connection.prepareStatement(
            "SELECT * FROM cartoes_credito WHERE cre_cli_id = ?",
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
                CartaoCredito c = new CartaoCredito();
                c.setId(rs.getInt("cre_id"));
                c.setNomeImpresso(rs.getString("cre_nome_impresso"));
                c.setPreferencial(rs.getBoolean("cre_is_preferencial"));
                c.setBandeiraCartao(bandeiraCartaoDAO.consultarByID(rs.getInt("cre_bcc_id")));
                c.setIdCliente(rs.getInt(rs.getInt("cre_cli_id")));
                cartoesCredito.add(c);
            }

            return cartoesCredito;    
        } catch (Exception e){
            throw e;
        } finally {
            connection.close();
            pst.close();
        }
    }

}
