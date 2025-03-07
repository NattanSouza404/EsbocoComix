package com.esboco_comix.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.esboco_comix.dao.IDAO;
import com.esboco_comix.model.entidades.Endereco;
import com.esboco_comix.utils.ConexaoFactory;

public class EnderecoDAO implements IDAO<Endereco> {

    @Override
    public Endereco inserir(Endereco e) throws Exception {
        Connection connection = ConexaoFactory.getConexao();

        PreparedStatement pst = connection.prepareStatement(
            "INSERT INTO enderecos("+
                "end_frase_curta, end_logradouro, end_tipo_logradouro, end_tipo_residencial, "+
                "end_numero, end_bairro, end_cep, end_cidade, end_estado, end_pais, "+
                "end_is_residencial, end_is_entrega, end_is_cobranca, end_observacoes, end_cli_id) "+
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);",
                Statement.RETURN_GENERATED_KEYS
        );

        try {
            pst.setString(1, e.getFraseCurta());
            pst.setString(2, e.getLogradouro());
            pst.setString(3, e.getTipoLogradouro());
            pst.setString(4, e.getTipoResidencial());
            pst.setString(5, e.getNumero());
            pst.setString(6, e.getBairro());
            pst.setString(7, e.getCep());
            pst.setString(8, e.getCidade());
            pst.setString(9, e.getEstado());
            pst.setString(10, e.getPais());
            pst.setBoolean(11, e.isResidencial());
            pst.setBoolean(12, e.isEntrega());
            pst.setBoolean(13, e.isCobranca());
            pst.setString(14, e.getObservacoes());
            pst.setInt(15, e.getIdCliente());
    
            if (pst.executeUpdate() == 0){
                throw new Exception("Inserção de endereço não executada!");
            }
            ResultSet rs = pst.getGeneratedKeys();
            Endereco enderecoInserido = null;
            if (rs.next()){
                enderecoInserido = consultarByID(rs.getInt(1));
            }

            return enderecoInserido;
        } catch (Exception ex){
            throw ex;
        } finally {
            connection.close();
            pst.close();
        }

    }
        
    @Override
    public List<Endereco> consultarTodos() throws Exception {
        throw new UnsupportedOperationException("Unimplemented method 'consultarTodos'");
    }

    @Override
    public Endereco consultarByID(int id) throws Exception {
        Connection connection = ConexaoFactory.getConexao();

        PreparedStatement pst = connection.prepareStatement(
            "SELECT * FROM enderecos WHERE end_id = ?"
        );

        try {
            pst.setInt(1, id);

            ResultSet rs = pst.executeQuery();
    
            if (!rs.next()) {
                throw new Exception("Nenhum registro encontrado de endereco.");
            }
    
            Endereco e = new Endereco();        
            e.setId(rs.getInt("end_id"));
            e.setFraseCurta(rs.getString("end_frase_curta"));
            e.setLogradouro(rs.getString("end_logradouro"));
            e.setTipoLogradouro(rs.getString("end_tipo_logradouro"));
            e.setTipoResidencial(rs.getString("end_tipo_residencial"));
            e.setNumero(rs.getString("end_numero"));
            e.setBairro(rs.getString("end_bairro"));
            e.setCep(rs.getString("end_cep"));
            e.setCidade(rs.getString("end_cidade"));
            e.setEstado(rs.getString("end_estado"));
            e.setPais(rs.getString("end_pais"));
            e.setResidencial(rs.getBoolean("end_is_residencial"));
            e.setEntrega(rs.getBoolean("end_is_entrega"));
            e.setCobranca(rs.getBoolean("end_is_cobranca"));
            e.setObservacoes(rs.getString("end_observacoes"));
            e.setIdCliente(rs.getInt("end_cli_id"));

            return e;    
        } catch (Exception e){
            throw e;
        } finally {
            connection.close();
            pst.close();
        }
    }

    public List<Endereco> consultarByIDCliente(int idCliente) throws Exception {
        Connection connection = ConexaoFactory.getConexao();

        PreparedStatement pst = connection.prepareStatement(
            "SELECT * FROM enderecos WHERE end_cli_id = ?",
            ResultSet.TYPE_SCROLL_INSENSITIVE,
            ResultSet.CONCUR_READ_ONLY
        );

        try {
            pst.setInt(1, idCliente);

            ResultSet rs = pst.executeQuery();
    
            if (!rs.next()) {
                throw new Exception("Esse cliente não possui nenhum registro de endereco.");
            }
            rs.beforeFirst();
    
            List<Endereco> enderecos = new ArrayList<>();
            while(rs.next()){
                Endereco e = new Endereco();        
                e.setId(rs.getInt("end_id"));
                e.setFraseCurta(rs.getString("end_frase_curta"));
                e.setLogradouro(rs.getString("end_logradouro"));
                e.setTipoLogradouro(rs.getString("end_tipo_logradouro"));
                e.setTipoResidencial(rs.getString("end_tipo_residencial"));
                e.setNumero(rs.getString("end_numero"));
                e.setBairro(rs.getString("end_bairro"));
                e.setCep(rs.getString("end_cep"));
                e.setCidade(rs.getString("end_cidade"));
                e.setEstado(rs.getString("end_estado"));
                e.setPais(rs.getString("end_pais"));
                e.setResidencial(rs.getBoolean("end_is_residencial"));
                e.setEntrega(rs.getBoolean("end_is_entrega"));
                e.setCobranca(rs.getBoolean("end_is_cobranca"));
                e.setObservacoes(rs.getString("end_observacoes"));
                e.setIdCliente(rs.getInt("end_cli_id"));
    
                enderecos.add(e);
            }

            return enderecos;    
        } catch (Exception e){
            throw e;
        } finally {
            connection.close();
            pst.close();
        }
    }

    @Override
    public Endereco atualizar(Endereco e) throws Exception {
        throw new UnsupportedOperationException("Unimplemented method 'atualizar'");
    }

    @Override
    public void deletar(Endereco e) throws Exception {
        throw new UnsupportedOperationException("Unimplemented method 'deletar'");
    }

}