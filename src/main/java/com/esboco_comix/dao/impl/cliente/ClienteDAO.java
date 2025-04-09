package com.esboco_comix.dao.impl.cliente;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.esboco_comix.dao.utils.DAOUtil;
import com.esboco_comix.model.entidades.Cliente;
import com.esboco_comix.model.entidades.Telefone;
import com.esboco_comix.model.enuns.Genero;
import com.esboco_comix.model.enuns.TipoTelefone;
import com.esboco_comix.utils.ConexaoFactory;

public class ClienteDAO {

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
            pst.setString(2, c.getGenero().name());
            pst.setDate(3, Date.valueOf(c.getDataNascimento()));
            pst.setString(4, c.getCpf());
            pst.setString(5, c.getEmail());
            pst.setString(6, c.getHashSenha());
            pst.setString(7, c.getSaltSenha());
            pst.setInt(8, c.getRanking());
            pst.setString(9, c.getTelefone().getTipo().name());
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
                clientes.add(mapearEntidade(rs));
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
            
            return mapearEntidade(rs);
        } catch (Exception e){
            throw e;
        } finally {
            connection.close();
            pst.close();
        }   
    }

    public Cliente atualizar(Cliente c) throws Exception {
        Connection conn = ConexaoFactory.getConexao(); 
    
        PreparedStatement pst = conn.prepareStatement(
            "UPDATE clientes set "+
                "cli_nome = ?, cli_genero = ?, cli_dt_nascimento = ?, cli_cpf = ?, cli_email = ?,"+
                "cli_tel_tipo = ?, cli_tel_ddd = ?, cli_tel_numero = ? "+
                "WHERE cli_id = ?"
        );
    
        try {
            pst.setString(1, c.getNome());
            pst.setString(2, c.getGenero().name());
            pst.setDate(3, Date.valueOf(c.getDataNascimento()));
            pst.setString(4, c.getCpf());
            pst.setString(5, c.getEmail());
            pst.setString(6, c.getTelefone().getTipo().name());
            pst.setString(7, c.getTelefone().getDdd());
            pst.setString(8, c.getTelefone().getNumero());
            pst.setInt(9, c.getId());
        
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

    public List<Cliente> consultarTodos(Cliente filtro) throws Exception {
        Connection conn = ConexaoFactory.getConexao();

        PreparedStatement pst = conn.prepareStatement(
            "SELECT * FROM clientes " +
                "WHERE (cli_nome LIKE COALESCE(?, cli_nome)) "+
                "AND (cli_genero LIKE COALESCE(?, cli_genero)) "+
                "AND (cli_dt_nascimento = COALESCE(?, cli_dt_nascimento)) "+
                "AND (cli_cpf LIKE COALESCE(?, cli_cpf)) "+
                "AND (cli_email LIKE COALESCE(?, cli_email)) "+
                "AND (cli_ranking = COALESCE(?, cli_ranking)) "+
                "AND (cli_is_ativo = ? OR ? IS NULL) ",
            ResultSet.TYPE_SCROLL_INSENSITIVE,
            ResultSet.CONCUR_READ_ONLY
        );

        try {
            DAOUtil.setParametroComCoringa(pst, 1, filtro.getNome());
            DAOUtil.setParametroComCoringa(pst, 2, filtro.getGenero());
            DAOUtil.setParametroComCoringa(pst, 3, filtro.getDataNascimento());
            DAOUtil.setParametroComCoringa(pst, 4, filtro.getCpf());
            DAOUtil.setParametroComCoringa(pst, 5, filtro.getEmail());
            DAOUtil.setParametroComCoringa(pst, 6, filtro.getRanking());

            pst.setObject(7, filtro.getIsAtivo(), java.sql.Types.BOOLEAN);
            pst.setObject(8, filtro.getIsAtivo(), java.sql.Types.BOOLEAN);

            ResultSet rs = pst.executeQuery();

            if (!rs.next()) {
                throw new Exception("Nenhum registro encontrado de cliente.");
            }
            rs.beforeFirst();

            List<Cliente> clientes = new ArrayList<>();

            while (rs.next()){                
                clientes.add(mapearEntidade(rs));
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

    public Cliente atualizarStatusCadastro(Cliente c) throws Exception {
        Connection conn = ConexaoFactory.getConexao(); 
    
        PreparedStatement pst = conn.prepareStatement(
            "UPDATE clientes set "+
                "cli_is_ativo = ? WHERE cli_id = ?"
        );
    
        try {
            pst.setBoolean(1, c.getIsAtivo());
            
            pst.setInt(2, c.getId());
        
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

    /***
     * Apenas esse método retorna o hash e o salt da senha do ClienteDAO.
     */
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

    private Cliente mapearEntidade(ResultSet rs) throws SQLException {
        Cliente c = new Cliente();  
        c.setId(rs.getInt("cli_id"));
        c.setNome(rs.getString("cli_nome"));
        c.setGenero(Genero.valueOf(rs.getString("cli_genero")));
        c.setDataNascimento(rs.getDate("cli_dt_nascimento").toLocalDate());
        c.setCpf(rs.getString("cli_cpf"));
        c.setEmail(rs.getString("cli_email"));
        c.setRanking(rs.getInt("cli_ranking"));
        c.setIsAtivo(rs.getBoolean("cli_is_ativo"));

        Telefone telefone = new Telefone();
        telefone.setTipo(TipoTelefone.valueOf(rs.getString("cli_tel_tipo")));
        telefone.setDdd(rs.getString("cli_tel_ddd"));
        telefone.setNumero(rs.getString("cli_tel_numero"));
        c.setTelefone(telefone);

        return c;
    }
    
}
