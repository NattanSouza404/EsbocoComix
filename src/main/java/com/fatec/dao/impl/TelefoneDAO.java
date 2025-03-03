package com.fatec.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import com.fatec.dao.IDAO;
import com.fatec.model.entidades.Telefone;
import com.fatec.utils.ConexaoFactory;
 
public class TelefoneDAO implements IDAO<Telefone> {

    @Override
    public Telefone inserir(Telefone t) throws Exception {
        Connection connection = ConexaoFactory.getConexao();

        PreparedStatement pst = connection.prepareStatement(
            "INSERT INTO telefones ("+
                "tel_ddd, tel_numero, tel_tipo) "+
                "VALUES (?, ?, ?);",
                Statement.RETURN_GENERATED_KEYS
        );

        pst.setString(1, t.getDdd());
        pst.setString(2, t.getNumero());
        pst.setString(3, t.getTipo());

        if (pst.executeUpdate() == 0){
            throw new Exception("Inserção de telefone não executada!");
        }

        ResultSet rs = pst.getGeneratedKeys();
        Telefone telefoneInserido = null;
        if (rs.next()){
            telefoneInserido = consultarByID(rs.getInt(1));
        }

        connection.close();
        pst.close();

        return telefoneInserido;
    }

    @Override
    public List<Telefone> consultarTodos() throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'consultarTodos'");
    }

    @Override
    public Telefone consultarByID(int id) throws Exception {
        Connection connection = ConexaoFactory.getConexao();

        PreparedStatement pst = connection.prepareStatement(
            "SELECT * FROM telefones WHERE tel_id = ?"
        );

        pst.setInt(1, id);

        ResultSet rs = pst.executeQuery();
        rs.next();

        Telefone t = new Telefone();
        t.setId(rs.getInt("tel_id"));
        t.setDdd(rs.getString("tel_ddd"));
        t.setNumero(rs.getString("tel_numero"));
        t.setTipo(rs.getString("tel_tipo"));

        connection.close();
        pst.close();

        return t;     
    }

    @Override
    public Telefone atualizar(Telefone t) throws Exception {
        Connection conn = ConexaoFactory.getConexao(); 
    
        PreparedStatement pst = conn.prepareStatement(
            "UPDATE telefones set "+
                "tel_ddd = ?, tel_numero = ?, tel_tipo = ? "+
                "WHERE tel_id = ?"
        );
    
        pst.setString(1, t.getDdd());
        pst.setString(2, t.getNumero());
        pst.setString(3, t.getTipo());

        pst.setInt(4, t.getId());
    
        if (pst.executeUpdate() == 0) {
            throw new Exception("Atualização não foi sucedida!");
        }

        pst.close();
        conn.close();

        return consultarByID(t.getId());
    }

    @Override
    public void deletar(Telefone t) throws Exception {
        Connection conn = ConexaoFactory.getConexao();

        PreparedStatement pst = conn.prepareStatement(
            "DELETE FROM telefones WHERE tel_id = ?"
        );
        
        pst.setInt(1, t.getId());
        
        if (pst.executeUpdate() == 0) {
            throw new Exception("Deleção não realizada. Telefone de id " + t.getId() + " não encontrado.");
        }

        pst.close();
        conn.close();
    }
    
}