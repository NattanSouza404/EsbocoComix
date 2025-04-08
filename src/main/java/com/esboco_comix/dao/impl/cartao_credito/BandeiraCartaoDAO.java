package com.esboco_comix.dao.impl.cartao_credito;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import com.esboco_comix.dao.IDAO;
import com.esboco_comix.model.enuns.BandeiraCartao;
import com.esboco_comix.utils.ConexaoFactory;

public class BandeiraCartaoDAO implements IDAO<BandeiraCartao> {

    @Override
    public BandeiraCartao inserir(BandeiraCartao e) throws Exception {
        throw new UnsupportedOperationException("Unimplemented method 'inserir'");
    }

    @Override
    public List<BandeiraCartao> consultarTodos() throws Exception {
        throw new UnsupportedOperationException("Unimplemented method 'consultarTodos'");
    }

    @Override
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

    @Override
    public BandeiraCartao atualizar(BandeiraCartao e) throws Exception {
        throw new UnsupportedOperationException("Unimplemented method 'atualizar'");
    }

    @Override
    public void deletar(BandeiraCartao e) throws Exception {
        throw new UnsupportedOperationException("Unimplemented method 'deletar'");
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
