package com.esboco_comix.dao.impl.quadrinho;

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
                quadrinhos.add(mapearEntidade(rs));
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
        Connection connection = ConexaoFactory.getConexao();

        PreparedStatement pst = connection.prepareStatement(
            "SELECT * FROM quadrinhos WHERE qua_id = ?"
        );

        try {
            pst.setInt(1, id);

            ResultSet rs = pst.executeQuery();
    
            if (!rs.next()){
                throw new Exception("Quadrinho não encontrado!");
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
    public Quadrinho atualizar(Quadrinho e) throws Exception {
        throw new UnsupportedOperationException("Unimplemented method 'atualizar'");
    }

    @Override
    public void deletar(Quadrinho e) throws Exception {
        throw new UnsupportedOperationException("Unimplemented method 'deletar'");
    }

    private Quadrinho mapearEntidade(ResultSet rs) throws SQLException {
        Quadrinho q = new Quadrinho();  
        q.setId(rs.getInt("qua_id"));

        q.setAno(rs.getDate("qua_ano").toLocalDate());
        q.setTitulo(rs.getString("qua_titulo"));
        q.setEditora(rs.getString("qua_editora"));
        q.setEdicao(rs.getInt("qua_edicao"));
        q.setIsbn(rs.getString("qua_isbn"));
        q.setNumeroPaginas(rs.getInt("qua_numero_paginas"));
        q.setSinopse(rs.getString("qua_sinopse"));
        q.setPreco(rs.getDouble("qua_preco"));
        q.setAutor(rs.getString("qua_autor"));

        q.setAltura(rs.getInt("qua_altura_cm"));
        q.setLargura(rs.getInt("qua_largura_cm"));
        q.setProfundidade(rs.getInt("qua_profundidade"));
        q.setPeso(rs.getInt("qua_peso_gramas"));
        q.setIsAtivo(rs.getBoolean("qua_is_ativo"));

        q.setCodigoBarras(rs.getString("qua_codigo_barras"));

        q.setUrlImagem(rs.getString("qua_url_imagem"));

        return q;
    }
    
}
