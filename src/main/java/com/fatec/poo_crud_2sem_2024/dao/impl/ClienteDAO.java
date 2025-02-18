package com.fatec.poo_crud_2sem_2024.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.fatec.poo_crud_2sem_2024.ConexaoFactory;
import com.fatec.poo_crud_2sem_2024.dao.IClienteDAO;
import com.fatec.poo_crud_2sem_2024.modelo.entidades.Cliente;

public class ClienteDAO implements IClienteDAO {

    @Override
    public void inserir(Cliente c) throws Exception {
        Connection connection = ConexaoFactory.getConexao();

        PreparedStatement preparedStatement = connection.prepareStatement(
            "INSERT INTO clientes (cli_nome) VALUES (?);"
        );

        preparedStatement.setString(0, c.getNome());

        if (preparedStatement.executeUpdate() == 0){
            throw new Exception("Inserção de cliente não executada!");
        }

        connection.close();
        preparedStatement.close();
    }

    @Override
    public List<Cliente> consultarTodos() throws Exception {
        Connection conn = ConexaoFactory.getConexao();

        PreparedStatement pstmt = conn.prepareStatement(
            "SELECT * FROM clientes;"
        );

        ResultSet rs = pstmt.executeQuery();

        List<Cliente> construcoes = new ArrayList<>();

        while (rs.next()){
            Cliente cst = new Cliente();        
            cst.setId(rs.getInt("cli_id"));
            cst.setNome(rs.getString("cli_nome"));

            construcoes.add(cst);
        }
        
        pstmt.close();
        conn.close();

        return construcoes;
    }

    @Override
    public Cliente consultarByID(int id) throws Exception {
        Connection connection = ConexaoFactory.getConexao();

        PreparedStatement preparedStatement = connection.prepareStatement(
            "SELECT * FROM clientes WHERE cli_id = ?"
        );

        preparedStatement.setInt(1, id);

        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();

        Cliente c = new Cliente();
        c.setId(resultSet.getInt("cli_id"));
        c.setNome(resultSet.getString("cli_nome"));

        connection.close();
        preparedStatement.close();

        return c;        
    }

    @Override
    public void atualizar(Cliente c) throws Exception {
        Connection conn = ConexaoFactory.getConexao(); 
    
        PreparedStatement pstmt = conn.prepareStatement(
            "UPDATE clientes SET cli_nome = ? WHERE cli_id = ?;"
        );
    
        pstmt.setString(1, c.getNome());
        pstmt.setInt(2, c.getId());
    
        if (pstmt.executeUpdate() == 0) {
            throw new Exception("Atualização não foi sucedida!");
        }

        pstmt.close();
        conn.close();
    }

    @Override
    public void deletar(Cliente c) throws Exception {
        Connection conn = ConexaoFactory.getConexao();

        PreparedStatement pstmt = conn.prepareStatement(
            "DELETE FROM clientes WHERE cli_id = ?"
        );
        
        pstmt.setInt(1, c.getId());
        
        pstmt.execute();

        pstmt.close();
        conn.close();
    }
    
}
