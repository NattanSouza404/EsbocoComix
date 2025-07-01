package com.esboco_comix.dao.impl.endereco;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.esboco_comix.model.entidades.Endereco;
import com.esboco_comix.model.enuns.TipoLogradouro;
import com.esboco_comix.model.enuns.TipoResidencial;
import com.esboco_comix.utils.ConexaoFactory;

public class EnderecoDAO {

    public Endereco inserir(Endereco e) throws Exception {
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
                throw new Exception("Inserção de endereço não executada!");
            }
            ResultSet rs = pst.getGeneratedKeys();
            Endereco enderecoInserido = null;
            if (rs.next()){
                enderecoInserido = consultarByID(rs.getInt(1));
            }

            return enderecoInserido;
        }
    }

    public Endereco consultarByID(int id) throws Exception {
        try (
            Connection connection = ConexaoFactory.getConexao();

            PreparedStatement pst = connection.prepareStatement(
                "SELECT * FROM enderecos WHERE end_id = ?"
            );
        ){
            pst.setInt(1, id);

            ResultSet rs = pst.executeQuery();
    
            if (!rs.next()) {
                throw new Exception("Endereço não encontrado.");
            }
            return mapearEntidade(rs);    
        }
    }

    public Endereco atualizar(Endereco e) throws Exception {
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
                throw new Exception("Atualização não foi sucedida!");
            }

            return consultarByID(e.getId());
        }
    }

    public void inativar(Endereco e) throws Exception {
        try (
            Connection conn = ConexaoFactory.getConexao();

            PreparedStatement pst = conn.prepareStatement(
                "UPDATE enderecos SET end_is_ativo = false WHERE end_id = ?;"
            );
        ) {
            pst.setInt(1, e.getId());

            if (pst.executeUpdate() == 0) {
                throw new Exception("Inativação não realizada. Endereço de id " + e.getId() + " não encontrado.");
            }

        }
    }

    public List<Endereco> consultarByIDCliente(int idCliente) throws Exception {
        try (
            Connection connection = ConexaoFactory.getConexao();

            PreparedStatement pst = connection.prepareStatement(
                "SELECT * FROM enderecos WHERE end_cli_id = ? AND end_is_ativo = true;",
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY
            );    
        ){
            pst.setInt(1, idCliente);

            ResultSet rs = pst.executeQuery();
    
            if (!rs.next()) {
                throw new Exception("Cliente não possui nenhum endereco.");
            }
            rs.beforeFirst();
    
            List<Endereco> enderecos = new ArrayList<>();
            while(rs.next()){
                enderecos.add(mapearEntidade(rs));
            }

            return enderecos;    
        }
    }

    private Endereco mapearEntidade(ResultSet rs) throws SQLException {
        Endereco e = new Endereco();        
        e.setId(rs.getInt("end_id"));
        e.setFraseCurta(rs.getString("end_frase_curta"));
        e.setLogradouro(rs.getString("end_logradouro"));
        e.setTipoLogradouro(TipoLogradouro.valueOf(rs.getString("end_tipo_logradouro")));
        e.setTipoResidencial(TipoResidencial.valueOf(rs.getString("end_tipo_residencial")));
        e.setNumero(rs.getString("end_numero"));
        e.setBairro(rs.getString("end_bairro"));
        e.setCep(rs.getString("end_cep"));
        e.setCidade(rs.getString("end_cidade"));
        e.setEstado(rs.getString("end_estado"));
        e.setPais(rs.getString("end_pais"));
        e.setIsResidencial(rs.getBoolean("end_is_residencial"));
        e.setIsEntrega(rs.getBoolean("end_is_entrega"));
        e.setIsCobranca(rs.getBoolean("end_is_cobranca"));
        e.setObservacoes(rs.getString("end_observacoes"));
        e.setIdCliente(rs.getInt("end_cli_id"));

        e.setIsAtivo(rs.getBoolean("end_is_ativo"));

        return e;
    }

}