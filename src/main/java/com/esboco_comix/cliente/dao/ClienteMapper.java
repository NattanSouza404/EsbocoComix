package com.esboco_comix.cliente.dao;

import com.esboco_comix.cliente.dominio.entidades.Cliente;
import com.esboco_comix.cliente.dominio.enuns.Genero;
import com.esboco_comix.cliente.dominio.enuns.TipoTelefone;
import com.esboco_comix.cliente.dominio.value_objects.Cpf;
import com.esboco_comix.cliente.dominio.value_objects.Email;
import com.esboco_comix.cliente.dominio.value_objects.Telefone;
import com.esboco_comix.core.dao.mapper.ResultSetMapper;

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
        c.setCpf(new Cpf(rs.getString("cli_cpf")));
        c.setEmail(new Email(rs.getString("cli_email")));
        c.setRanking(rs.getInt("cli_ranking"));
        c.setIsAtivo(rs.getBoolean("cli_is_ativo"));

        c.setTelefone(
            new Telefone(
                rs.getString("cli_tel_ddd"),
                rs.getString("cli_tel_numero"),
                TipoTelefone.valueOf(rs.getString("cli_tel_tipo"))
            )
        );

        return c;
    }

    @Override
    public Cliente mapearDTO(ResultSet rs) throws SQLException {
        throw new UnsupportedOperationException("Sem implementação para mapearDTO");
    }
}
