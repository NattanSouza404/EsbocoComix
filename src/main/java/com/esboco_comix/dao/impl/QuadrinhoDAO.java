package com.esboco_comix.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.esboco_comix.dao.IDAO;
import com.esboco_comix.model.entidades.Quadrinho;
import com.esboco_comix.utils.ConexaoFactory;

public class QuadrinhoDAO implements IDAO<Quadrinho> {

    @Override
    public Quadrinho inserir(Quadrinho e) throws Exception {
        throw new UnsupportedOperationException("Unimplemented method 'inserir'");
    }

    @Override
    public List<Quadrinho> consultarTodos() throws Exception {
        Connection conn = ConexaoFactory.getConexao();

        PreparedStatement pst = conn.prepareStatement(
            "SELECT * FROM quadrinhos;",
            ResultSet.TYPE_SCROLL_INSENSITIVE,
            ResultSet.CONCUR_READ_ONLY
        );

        try {
            ResultSet rs = pst.executeQuery();

            if (!rs.next()) {
                throw new Exception("Nenhum registro encontrado de quadrinho.");
            }
            rs.beforeFirst();

            List<Quadrinho> quadrinhos = new ArrayList<>();

            while (rs.next()){                
                quadrinhos.add(mapearResultToQuadrinho(rs));
            }

            return quadrinhos;
        }
        catch (Exception e){
            throw e;
        } finally {
            pst.close();
            conn.close();
        }
    }

    @Override
    public Quadrinho consultarByID(int id) throws Exception {
        throw new UnsupportedOperationException("Unimplemented method 'consultarByID'");
    }

    @Override
    public Quadrinho atualizar(Quadrinho e) throws Exception {
        throw new UnsupportedOperationException("Unimplemented method 'atualizar'");
    }

    @Override
    public void deletar(Quadrinho e) throws Exception {
        throw new UnsupportedOperationException("Unimplemented method 'deletar'");
    }

    private Quadrinho mapearResultToQuadrinho(ResultSet rs) throws SQLException {
        Quadrinho q = new Quadrinho();  
        q.setId(rs.getInt("qua_id"));

        return q;
    }
    
}
