package com.esboco_comix.dao.mapper.impl;

import com.esboco_comix.dao.mapper.ResultSetMapper;
import com.esboco_comix.model.entidades.Cliente;
import com.esboco_comix.model.entidades.Telefone;
import com.esboco_comix.model.enuns.Genero;
import com.esboco_comix.model.enuns.TipoTelefone;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ClienteMapper implements ResultSetMapper<Cliente, Cliente> {

    @Override
    public Cliente mapearEntidade(ResultSet rs) throws SQLException {
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

    @Override
    public Cliente mapearDTO(ResultSet rs) throws SQLException {
        throw new UnsupportedOperationException("Sem implementação para mapearDTO");
    }
}
