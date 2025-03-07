package com.esboco_comix.service;

import java.util.List;

import com.esboco_comix.dao.impl.EnderecoDAO;
import com.esboco_comix.model.entidades.Endereco;

public class EnderecoService {

    private EnderecoDAO enderecoDAO = new EnderecoDAO();

    public Endereco inserir(Endereco e) throws Exception{
        return enderecoDAO.inserir(e);
    }

    public List<Endereco> consultarByIDCliente(int id) throws Exception {
        return enderecoDAO.consultarByIDCliente(id);
    }

}
