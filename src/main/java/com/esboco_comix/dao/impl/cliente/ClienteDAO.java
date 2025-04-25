package com.esboco_comix.dao.impl.cliente;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.esboco_comix.dto.FiltrarClienteDTO;
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

    /***
     * Operador ILIKE somente suportado pelo PostgreSQL
     */
    public List<Cliente> consultarTodos(FiltrarClienteDTO filtro) throws Exception {
        Connection conn = ConexaoFactory.getConexao();

        StringBuilder query = new StringBuilder("SELECT * FROM clientes WHERE 1=1");

        List<Object> params = new ArrayList<>();

        if (filtro.getNome() != null) {
            query.append(" AND cli_nome ILIKE ?");
            params.add("%" + filtro.getNome() + "%");
        }

        if (filtro.getGenero() != null) {
            query.append(" AND cli_genero = ?");
            params.add(filtro.getGenero());
        }

        if (filtro.getDataNascimento() != null) {
            query.append(" AND cli_dt_nascimento = ?");
            params.add(filtro.getDataNascimento());
        }

        if (filtro.getCpf() != null) {
            query.append(" AND cli_cpf LIKE ?");
            params.add("%" + filtro.getCpf() + "%");
        }

        if (filtro.getEmail() != null) {
            query.append(" AND cli_email ILIKE ?");
            params.add("%" + filtro.getEmail() + "%");
        }

        if (filtro.getIsAtivo() != null) {
            query.append(" AND cli_is_ativo = ?");
            params.add(filtro.getIsAtivo());
        }

        if (filtro.getRanking() != null) {
            query.append(" AND cli_ranking = ?");
            params.add(filtro.getRanking());
        }

        PreparedStatement pst = conn.prepareStatement(
            query.toString(),
            ResultSet.TYPE_SCROLL_INSENSITIVE,
            ResultSet.CONCUR_READ_ONLY
        );
        
        try {

            for (int i = 0; i < params.size(); i++) {
               
                if (params.get(i) instanceof Enum){
                    pst.setObject(i + 1, ((Enum<?>) params.get(i)).name());
                    continue;
                }

                pst.setObject(i + 1, params.get(i));
            }

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

    public Cliente consultarByIDPedido(int idPedido) throws Exception {
        Connection connection = ConexaoFactory.getConexao();

        PreparedStatement pst = connection.prepareStatement(
            "SELECT * FROM clientes WHERE cli_id = ("+
            "   SELECT ped_cli_id FROM pedidos"+
            "   WHERE ped_id = ?"+
            ");"
        );

        try {
            pst.setInt(1, idPedido);

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
