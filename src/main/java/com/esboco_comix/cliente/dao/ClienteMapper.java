package com.esboco_comix.cliente.dao;

import com.esboco_comix.cliente.dominio.entidades.Cliente;
import com.esboco_comix.cliente.dominio.enuns.Genero;
import com.esboco_comix.cliente.dominio.enuns.TipoTelefone;
import com.esboco_comix.cliente.dominio.value_objects.Cpf;
import com.esboco_comix.cliente.dominio.value_objects.Email;
import com.esboco_comix.cliente.dominio.value_objects.Telefone;
import com.esboco_comix.core.dao.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ClienteMapper implements ResultSetMapper<Cliente, Cliente> {

    @Override
    public Cliente mapearEntidade(ResultSet rs) throws SQLException {
        return Cliente.builder()
            .id(rs.getInt("cli_id"))
            .nome(rs.getString("cli_nome"))
            .genero(Genero.valueOf(rs.getString("cli_genero")))
            .dataNascimento(rs.getDate("cli_dt_nascimento").toLocalDate())
            .cpf(new Cpf(rs.getString("cli_cpf")))
            .email(new Email(rs.getString("cli_email")))
            .ranking(rs.getInt("cli_ranking"))
            .isAtivo(rs.getBoolean("cli_is_ativo"))

            .telefone(
                new Telefone(
                    rs.getString("cli_tel_ddd"),
                    rs.getString("cli_tel_numero"),
                    TipoTelefone.valueOf(rs.getString("cli_tel_tipo"))
                )
            )
        .build();
    }

    @Override
    public Cliente mapearDTO(ResultSet rs) throws SQLException {
        throw new UnsupportedOperationException("Sem implementação para mapearDTO");
    }
}
