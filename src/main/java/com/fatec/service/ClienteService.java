package com.fatec.service;

import java.util.List;

import com.fatec.dao.impl.ClienteDAO;
import com.fatec.model.entidades.Cliente;

public class ClienteService {
    private ClienteDAO clienteDAO = new ClienteDAO();
    private CartaoCreditoService cartaoCreditoService = new CartaoCreditoService();

    public void inserir(Cliente c) throws Exception {
        clienteDAO.inserir(c);
        //cartaoCreditoService.inserir(c.getCartoesCliente());
    }

    public List<Cliente> consultarTodos() throws Exception {
        return clienteDAO.consultarTodos();
    }

    public Cliente consultarByID(int id) throws Exception {
        return clienteDAO.consultarByID(id);
    }

    public Cliente atualizar(Cliente c) throws Exception {
        return clienteDAO.atualizar(c);
    }

    public void deletar(Cliente c) throws Exception {
        clienteDAO.deletar(c);
    }

}
