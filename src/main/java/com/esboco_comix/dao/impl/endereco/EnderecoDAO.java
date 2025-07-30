package com.esboco_comix.dao.impl.endereco;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.esboco_comix.dao.mapper.impl.EnderecoMapper;
import com.esboco_comix.model.entidades.Endereco;
import com.esboco_comix.utils.ConexaoFactory;

public class EnderecoDAO {

    private final EnderecoMapper enderecoMapper = new EnderecoMapper();

    public Endereco inserir(Endereco e) {
        try (
            Connection connection = ConexaoFactory.getConexao();

            PreparedStatement pst = connection.prepareStatement(
                "INSERT INTO enderecos("+
                    "end_frase_curta, end_logradouro, end_tipo_logradouro, end_tipo_residencial, "+
                    "end_numero, end_bairro, end_cep, end_cidade, end_estado, end_pais, "+
                    "end_is_residencial, end_is_entrega, end_is_cobranca, end_observacoes, end_cli_id) "+
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);",
                    Statement.RETURN_GENERATED_KEYS
            );
        ) {
            pst.setString(1, e.getFraseCurta());
            pst.setString(2, e.getLogradouro());
            pst.setString(3, e.getTipoLogradouro().name());
            pst.setString(4, e.getTipoResidencial().name());
            pst.setString(5, e.getNumero());
            pst.setString(6, e.getBairro());
            pst.setString(7, e.getCep());
            pst.setString(8, e.getCidade());
            pst.setString(9, e.getEstado());
            pst.setString(10, e.getPais());
            pst.setBoolean(11, e.getIsResidencial());
            pst.setBoolean(12, e.getIsEntrega());
            pst.setBoolean(13, e.getIsCobranca());
            pst.setString(14, e.getObservacoes());
            pst.setInt(15, e.getIdCliente());
    
            if (pst.executeUpdate() == 0){
                throw new IllegalStateException("Inserção de endereço não executada!");
            }
            ResultSet rs = pst.getGeneratedKeys();
            Endereco enderecoInserido = null;
            if (rs.next()){
                enderecoInserido = consultarByID(rs.getInt(1));
            }

            return enderecoInserido;
        } catch (Exception ex){
            throw new IllegalStateException(ex);
        }
    }

    public Endereco consultarByID(int id) {
        try (
            Connection connection = ConexaoFactory.getConexao();

            PreparedStatement pst = connection.prepareStatement(
                "SELECT * FROM enderecos WHERE end_id = ?"
            );
        ){
            pst.setInt(1, id);

            ResultSet rs = pst.executeQuery();
    
            if (!rs.next()) {
                throw new IllegalStateException("Endereço não encontrado.");
            }
            return enderecoMapper.mapearEntidade(rs);
        } catch (Exception e){
            throw new IllegalStateException(e);
        }
    }

    public Endereco atualizar(Endereco e) {
        try (
            Connection conn = ConexaoFactory.getConexao(); 
    
            PreparedStatement pst = conn.prepareStatement(
                "UPDATE enderecos "+
                    "SET end_frase_curta = ?, end_logradouro = ?, "+
                    "end_tipo_logradouro = ?, end_tipo_residencial = ?, end_numero = ?, "+
                    "end_bairro = ?, end_cep = ?, end_cidade = ?, end_estado = ?, end_pais = ?, "+
                    "end_is_residencial = ?, end_is_entrega = ?, end_is_cobranca = ?, "+
                    "end_observacoes = ?, end_cli_id = ? "+
                    "WHERE end_id = ?;"
            );
        ){
            pst.setString(1, e.getFraseCurta());
            pst.setString(2, e.getLogradouro());
            pst.setString(3, e.getTipoLogradouro().name());
            pst.setString(4, e.getTipoResidencial().name());
            pst.setString(5, e.getNumero());
            pst.setString(6, e.getBairro());
            pst.setString(7, e.getCep());
            pst.setString(8, e.getCidade());
            pst.setString(9, e.getEstado());
            pst.setString(10, e.getPais());
            pst.setBoolean(11, e.getIsResidencial());
            pst.setBoolean(12, e.getIsEntrega());
            pst.setBoolean(13, e.getIsCobranca());
            pst.setString(14, e.getObservacoes());
            pst.setInt(15, e.getIdCliente());
            pst.setInt(16, e.getId());
        
            if (pst.executeUpdate() == 0) {
                throw new IllegalStateException("Atualização não foi sucedida!");
            }

            return consultarByID(e.getId());
        } catch (Exception ex){
            throw new IllegalStateException(ex);
        }
    }

    public void inativar(Endereco e) {
        try (
            Connection conn = ConexaoFactory.getConexao();

            PreparedStatement pst = conn.prepareStatement(
                "UPDATE enderecos SET end_is_ativo = false WHERE end_id = ?;"
            );
        ) {
            pst.setInt(1, e.getId());

            if (pst.executeUpdate() == 0) {
                throw new IllegalStateException("Inativação não realizada. Endereço de id " + e.getId() + " não encontrado.");
            }

        } catch (Exception ex){
            throw new IllegalStateException(ex);
        }
    }

    public List<Endereco> consultarByIDCliente(int idCliente) {
        try (
            Connection connection = ConexaoFactory.getConexao();

            PreparedStatement pst = connection.prepareStatement(
                "SELECT * FROM enderecos WHERE end_cli_id = ? AND end_is_ativo = true ORDER BY end_id;",
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY
            );    
        ){
            pst.setInt(1, idCliente);

            ResultSet rs = pst.executeQuery();
    
            if (!rs.next()) {
                throw new IllegalStateException("Cliente não possui nenhum endereco.");
            }
            rs.beforeFirst();
    
            List<Endereco> enderecos = new ArrayList<>();
            while(rs.next()){
                enderecos.add(enderecoMapper.mapearEntidade(rs));
            }

            return enderecos;    
        } catch (Exception e){
            throw new IllegalStateException(e);
        }
    }

}