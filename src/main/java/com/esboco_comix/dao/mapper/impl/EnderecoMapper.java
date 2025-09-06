package com.esboco_comix.dao.mapper.impl;

import com.esboco_comix.dao.mapper.ResultSetMapper;
import com.esboco_comix.model.entidades.Endereco;
import com.esboco_comix.model.enuns.TipoLogradouro;
import com.esboco_comix.model.enuns.TipoResidencial;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EnderecoMapper implements ResultSetMapper<Endereco, Endereco> {

    @Override
    public Endereco mapearEntidade(ResultSet rs) throws SQLException {
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

    @Override
    public Endereco mapearDTO(ResultSet rs) throws SQLException {
        throw new UnsupportedOperationException("Sem implementação para mapearDTO");
    }
}
