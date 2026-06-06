package com.esboco_comix.service.impl;

import java.util.List;

import com.esboco_comix.dao.impl.endereco.EnderecoDAO;
import com.esboco_comix.model.entidades.Endereco;
import com.esboco_comix.validador.impl.endereco.EnderecoValidador;

public class EnderecoService {

    private EnderecoDAO enderecoDAO = new EnderecoDAO();
    private EnderecoValidador enderecoValidador = new EnderecoValidador();

    public Endereco inserir(Endereco e) {
        // TODO: ao inserir um cliente, acabamos verificando o endereço 2 vezes
        enderecoValidador.validar(e);
        return enderecoDAO.inserir(e);
    }

    public List<Endereco> consultarByIDCliente(int id)  {
        return enderecoDAO.consultarByIDCliente(id);
    }

    public Endereco atualizar(Endereco e) {
        enderecoValidador.validar(e);
        return enderecoDAO.atualizar(e);
    }

    public void inativar(Endereco e)  {
        enderecoDAO.inativar(e);
    }

}
