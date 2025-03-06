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
import com.fatec.model.entidades.Telefone;
import com.fatec.utils.ConexaoFactory;

/***
 * Dentro do ClienteDAO, não retorno nem o hash da senha, nem o salt
 */

public class ClienteDAO implements IDAO<Cliente> {

    @Override
    public Cliente inserir(Cliente c) throws Exception {
        Connection connection = ConexaoFactory.getConexao();

        PreparedStatement pst = connection.prepareStatement(
            "INSERT INTO clientes("+
                "cli_nome, cli_genero, cli_dt_nascimento, cli_cpf, cli_email, "+
                "cli_hash_senha, cli_salt_senha, cli_ranking, "+
                "cli_tel_tipo, cli_tel_ddd, cli_tel_numero, cli_is_ativo) "+
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);",
                Statement.RETURN_GENERATED_KEYS
        );

        try {
            pst.setString(1, c.getNome());
            pst.setString(2, c.getGenero());
            pst.setDate(3, Date.valueOf(c.getDataNascimento()));
            pst.setString(4, c.getCpf());
            pst.setString(5, c.getEmail());
            pst.setString(6, c.getHashSenha());
            pst.setString(7, c.getSaltSenha());
            pst.setInt(8, c.getRanking());
            pst.setString(9, c.getTelefone().getTipo());
            pst.setString(10, c.getTelefone().getDdd());
            pst.setString(11, c.getTelefone().getNumero());
            pst.setBoolean(12, true);
    
            if (pst.executeUpdate() == 0){
                throw new Exception("Inserção de cliente não executada!");
            }
    
            ResultSet rs = pst.getGeneratedKeys();
            Cliente clienteInserido = null;
            if (rs.next()){
                clienteInserido = consultarByID(rs.getInt(1));
            }

            return clienteInserido;
            
        } catch (Exception e){
            throw e;
        } finally {
            connection.close();
            pst.close();
        }
    }

    @Override
    public List<Cliente> consultarTodos() throws Exception {
        Connection conn = ConexaoFactory.getConexao();

        PreparedStatement pst = conn.prepareStatement(
            "SELECT * FROM clientes;",
            ResultSet.TYPE_SCROLL_INSENSITIVE,
            ResultSet.CONCUR_READ_ONLY
        );

        try {
            ResultSet rs = pst.executeQuery();

            if (!rs.next()) {
                throw new Exception("Nenhum registro encontrado de cliente.");
            }
            rs.beforeFirst();

            List<Cliente> clientes = new ArrayList<>();

            while (rs.next()){
                Cliente c = new Cliente();        
                c.setId(rs.getInt("cli_id"));
                c.setNome(rs.getString("cli_nome"));
                c.setGenero(rs.getString("cli_genero"));
                c.setDataNascimento(rs.getDate("cli_dt_nascimento").toLocalDate());
                c.setCpf(rs.getString("cli_cpf"));
                c.setEmail(rs.getString("cli_email"));
                c.setRanking(rs.getInt("cli_ranking"));
                c.setAtivo(rs.getBoolean("cli_is_ativo"));

                Telefone telefone = new Telefone();
                telefone.setTipo(rs.getString("cli_tel_tipo"));
                telefone.setDdd(rs.getString("cli_tel_ddd"));
                telefone.setNumero(rs.getString("cli_tel_numero"));
                c.setTelefone(telefone);
                
                clientes.add(c);
            }

            return clientes;
        }
        catch (Exception e){
            throw e;
        } finally {
            pst.close();
            conn.close();
        }
    }

    @Override
    public Cliente consultarByID(int id) throws Exception {
        Connection connection = ConexaoFactory.getConexao();

        PreparedStatement pst = connection.prepareStatement(
            "SELECT * FROM clientes WHERE cli_id = ?"
        );

        try {
            pst.setInt(1, id);

            ResultSet rs = pst.executeQuery();
    
            if (!rs.next()){
                throw new Exception("Cliente não encontrado!");
            }
    
            Cliente c = new Cliente();
            c.setId(rs.getInt("cli_id"));
            c.setNome(rs.getString("cli_nome"));
            c.setGenero(rs.getString("cli_genero"));
            c.setDataNascimento(rs.getDate("cli_dt_nascimento").toLocalDate());
            c.setCpf(rs.getString("cli_cpf"));
            c.setEmail(rs.getString("cli_email"));
            c.setRanking(rs.getInt("cli_ranking"));
            c.setAtivo(rs.getBoolean("cli_is_ativo"));
    
            Telefone telefone = new Telefone();
            telefone.setTipo(rs.getString("cli_tel_tipo"));
            telefone.setDdd(rs.getString("cli_tel_ddd"));
            telefone.setNumero(rs.getString("cli_tel_numero"));
            c.setTelefone(telefone);

            return c;
        } catch (Exception e){
            throw e;
        } finally {
            connection.close();
            pst.close();
        }   
    }

    @Override
    public Cliente atualizar(Cliente c) throws Exception {
        Connection conn = ConexaoFactory.getConexao(); 
    
        PreparedStatement pst = conn.prepareStatement(
            "UPDATE clientes set "+
                "cli_nome = ?, cli_genero = ?, cli_dt_nascimento = ?, cli_cpf = ?, cli_email = ?,"+
                "cli_ranking = ?, cli_tel_tipo = ?, cli_tel_ddd = ?, cli_tel_numero = ?, cli_is_ativo = ? "+
                "WHERE cli_id = ?"
        );
    
        try {
            pst.setString(1, c.getNome());
            pst.setString(2, c.getGenero());
            pst.setDate(3, Date.valueOf(c.getDataNascimento()));
            pst.setString(4, c.getCpf());
            pst.setString(5, c.getEmail());
            pst.setInt(6, c.getRanking());
            pst.setString(7, c.getTelefone().getTipo());
            pst.setString(8, c.getTelefone().getDdd());
            pst.setString(9, c.getTelefone().getNumero());
            pst.setBoolean(10, c.isAtivo());
            pst.setInt(11, c.getId());
        
            if (pst.executeUpdate() == 0) {
                throw new Exception("Atualização não foi sucedida!");
            }

            return consultarByID(c.getId());
        } catch (Exception e){
            throw e;
        } finally {
            pst.close();
            conn.close();
        } 
    }

    @Override
    public void deletar(Cliente c) throws Exception {
        Connection conn = ConexaoFactory.getConexao();

        PreparedStatement pst = conn.prepareStatement(
            "DELETE FROM clientes WHERE cli_id = ?"
        );

        try {
            pst.setInt(1, c.getId());

            if (pst.executeUpdate() == 0) {
                throw new Exception("Deleção não realizada. Cliente de id " + c.getId() + " não encontrado.");
            }

        } catch (Exception e){
            throw e;
        } finally {
            pst.close();
            conn.close();
        }
    }

    public Cliente atualizarSenha(Cliente c) throws Exception {
        Connection conn = ConexaoFactory.getConexao(); 
    
        PreparedStatement pst = conn.prepareStatement(
            "UPDATE clientes set "+
                "cli_hash_senha = ?, cli_salt_senha = ? "+
                "WHERE cli_id = ?"
        );
    
        try {
            pst.setString(1, c.getHashSenha());
            pst.setString(2, c.getSaltSenha());
    
            pst.setInt(3, c.getId());
        
            if (pst.executeUpdate() == 0) {
                throw new Exception("Atualização não foi sucedida!");
            }
    
            return consultarByID(c.getId());

        } catch (Exception e){
            throw e;
        } finally {
            pst.close();
            conn.close();
        }
    }

    public Cliente consultarHashSaltPorID(int id) throws Exception {
        Connection connection = ConexaoFactory.getConexao();

        PreparedStatement pst = connection.prepareStatement(
            "SELECT cli_id, cli_hash_senha, cli_salt_senha FROM clientes WHERE cli_id = ?;"
        );

        try {
            pst.setInt(1, id);

            ResultSet rs = pst.executeQuery();
    
            if (!rs.next()){
                throw new Exception("Cliente não encontrado!");
            }
    
            Cliente c = new Cliente();
            c.setId(rs.getInt("cli_id"));
            c.setHashSenha(rs.getString("cli_hash_senha"));
            c.setSaltSenha(rs.getString("cli_salt_senha"));
            return c;
        } catch (Exception e){
            throw e;
        } finally {
            connection.close();
            pst.close();
        }
    }
    
}
