package com.esboco_comix.cliente.service;

import java.util.List;

import com.esboco_comix.cliente.dao.EnderecoDAO;
import com.esboco_comix.cliente.dominio.entidades.Endereco;

public class EnderecoService {

    private EnderecoDAO enderecoDAO = new EnderecoDAO();

    public Endereco inserir(Endereco e) {
        // TODO: ao inserir um cliente, acabamos verificando o endereço 2 vezes
        e.validar();
        return enderecoDAO.inserir(e);
    }

    public List<Endereco> consultarByIDCliente(int id)  {
        return enderecoDAO.consultarByIDCliente(id);
    }

    public Endereco atualizar(Endereco e) {
        e.validar();
        return enderecoDAO.atualizar(e);
    }

    public void inativar(Endereco e)  {
        enderecoDAO.inativar(e);
    }

}
