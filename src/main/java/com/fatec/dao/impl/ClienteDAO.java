package com.fatec.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.fatec.dao.IDAO;
import com.fatec.model.entidades.Cliente;
import com.fatec.utils.ConexaoFactory;

public class ClienteDAO implements IDAO<Cliente> {

    @Override
    public Cliente inserir(Cliente c) throws Exception {
        Connection connection = ConexaoFactory.getConexao();

        PreparedStatement preparedStatement = connection.prepareStatement(
            "INSERT INTO clientes("+
                "cli_nome, cli_genero, cli_dt_nascimento, cli_cpf, cli_email,"+
                "cli_hash_senha, cli_salt_senha, cli_ranking, cli_is_ativo)"+
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);",
                Statement.RETURN_GENERATED_KEYS
        );

        preparedStatement.setString(1, c.getNome());
        preparedStatement.setString(2, c.getGenero());
        preparedStatement.setDate(3, Date.valueOf(c.getDataNascimento()));
        preparedStatement.setString(4, c.getCpf());
        preparedStatement.setString(5, c.getEmail());
        preparedStatement.setString(6, c.getHashSenha());
        preparedStatement.setString(7, c.getSaltSenha());
        preparedStatement.setInt(8, c.getRanking());
        preparedStatement.setBoolean(9, c.isAtivo());
        
        if (preparedStatement.executeUpdate() == 0){
            throw new Exception("Inserção de cliente não executada!");
        }

        ResultSet resultSet = preparedStatement.getGeneratedKeys();
        Cliente clienteInserido = null;
        if (resultSet.next()){
            clienteInserido = consultarByID(resultSet.getInt(1));
        }

        connection.close();
        preparedStatement.close();

        return clienteInserido;
    }

    @Override
    public List<Cliente> consultarTodos() throws Exception {
        Connection conn = ConexaoFactory.getConexao();

        PreparedStatement pstmt = conn.prepareStatement(
            "SELECT * FROM clientes;"
        );

        ResultSet rs = pstmt.executeQuery();

        List<Cliente> clientes = new ArrayList<>();

        while (rs.next()){
            Cliente c = new Cliente();        
            c.setId(rs.getInt("cli_id"));
            c.setNome(rs.getString("cli_nome"));
            c.setGenero(rs.getString("cli_genero"));
            c.setDataNascimento(rs.getDate("cli_dt_nascimento").toLocalDate());
            c.setCpf(rs.getString("cli_cpf"));
            c.setEmail(rs.getString("cli_email"));

            //Não retorno hash_senha e salt_senha
            c.setRanking(rs.getInt("cli_ranking"));
            c.setAtivo(rs.getBoolean("cli_is_ativo"));
            
            clientes.add(c);
        }
        
        pstmt.close();
        conn.close();

        return clientes;
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
        c.setGenero(resultSet.getString("cli_genero"));
        c.setDataNascimento(resultSet.getDate("cli_dt_nascimento").toLocalDate());
        c.setCpf(resultSet.getString("cli_cpf"));
        c.setEmail(resultSet.getString("cli_email"));

        //Não retorno hash_senha e salt_senha
        c.setRanking(resultSet.getInt("cli_ranking"));
        c.setAtivo(resultSet.getBoolean("cli_is_ativo"));

        connection.close();
        preparedStatement.close();

        return c;        
    }

    @Override
    public Cliente atualizar(Cliente c) throws Exception {
        Connection conn = ConexaoFactory.getConexao(); 
    
        PreparedStatement pstmt = conn.prepareStatement(
            "UPDATE clientes set "+
                "cli_nome = ?, cli_genero = ?, cli_dt_nascimento = ?, cli_cpf = ?, cli_email = ?,"+
                "cli_ranking = ?, cli_is_ativo = ? "+
                "WHERE cli_id = ?"
        );
    
        pstmt.setString(1, c.getNome());
        pstmt.setString(2, c.getGenero());
        pstmt.setDate(3, Date.valueOf(c.getDataNascimento()));
        pstmt.setString(4, c.getCpf());
        pstmt.setString(5, c.getEmail());
        pstmt.setInt(6, c.getRanking());
        pstmt.setBoolean(7, c.isAtivo());

        pstmt.setInt(8, c.getId());
    
        if (pstmt.executeUpdate() == 0) {
            throw new Exception("Atualização não foi sucedida!");
        }

        pstmt.close();
        conn.close();

        return consultarByID(c.getId());
    }

    public Cliente atualizarSenha(Cliente c) throws Exception {
        Connection conn = ConexaoFactory.getConexao(); 
    
        PreparedStatement pstmt = conn.prepareStatement(
            "UPDATE clientes set "+
                "cli_hash_senha = ?, cli_salt_senha = ? "+
                "WHERE cli_id = ?"
        );
    
        pstmt.setString(1, c.getHashSenha());
        pstmt.setString(2, c.getSaltSenha());

        pstmt.setInt(3, c.getId());
    
        if (pstmt.executeUpdate() == 0) {
            throw new Exception("Atualização não foi sucedida!");
        }

        pstmt.close();
        conn.close();

        return consultarByID(c.getId());
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
