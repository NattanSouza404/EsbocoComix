package com.esboco_comix.service.impl;

import java.util.List;

import com.esboco_comix.dao.impl.endereco.EnderecoDAO;
import com.esboco_comix.model.entidades.Endereco;

public class EnderecoService {

    private EnderecoDAO enderecoDAO = new EnderecoDAO();

    public Endereco inserir(Endereco e) {
        return enderecoDAO.inserir(e);
    }

    public List<Endereco> consultarByIDCliente(int id)  {
        return enderecoDAO.consultarByIDCliente(id);
    }

    public Endereco atualizar(Endereco e) {
        return enderecoDAO.atualizar(e);
    }

    public void inativar(Endereco e)  {
        enderecoDAO.inativar(e);
    }

}
