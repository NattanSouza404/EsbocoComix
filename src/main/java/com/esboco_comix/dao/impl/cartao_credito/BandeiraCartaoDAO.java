package com.esboco_comix.dao.impl.cartao_credito;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.esboco_comix.model.enuns.BandeiraCartao;
import com.esboco_comix.utils.ConexaoFactory;

public class BandeiraCartaoDAO {

    public BandeiraCartao consultarByID(int id) throws Exception {
        Connection connection = ConexaoFactory.getConexao();

        PreparedStatement pst = connection.prepareStatement(
            "SELECT * FROM bandeiras_cartao_credito WHERE bcc_id = ?;"
        );

        try {
            pst.setInt(1, id);

            ResultSet rs = pst.executeQuery();
    
            if (!rs.next()) {
                throw new Exception("Bandeira de cartão de crédito não encontrada.");
            }
            return BandeiraCartao.getBandeiraByNome(rs.getString("bcc_nome"));
        } catch (Exception e){
            throw e;
        } finally {
            connection.close();
            pst.close();
        }
    }

    public int consultarIDByNome(String nome) throws Exception {
        Connection connection = ConexaoFactory.getConexao();

        PreparedStatement pst = connection.prepareStatement(
            "SELECT * FROM bandeiras_cartao_credito WHERE bcc_nome = ?;"
        );

        try {
            pst.setString(1, nome);

            ResultSet rs = pst.executeQuery();
    
            if (!rs.next()) {
                throw new Exception("Bandeira de cartão de crédito não encontrada.");
            }

            return rs.getInt("bcc_id");  
        } catch (Exception e){
            throw e;
        } finally {
            connection.close();
            pst.close();
        }
    }
    
}
