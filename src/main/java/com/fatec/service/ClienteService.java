package com.fatec.service;

import java.util.List;

import com.fatec.dao.impl.ClienteDAO;
import com.fatec.model.entidades.Cliente;

public class ClienteService {
    private ClienteDAO clienteDAO = new ClienteDAO();
    private CartaoCreditoService cartaoCreditoService = new CartaoCreditoService();
    private ClienteValidador clienteValidador = new ClienteValidador();

    public Cliente inserir(Cliente c) throws Exception {
        clienteValidador.validar(c);
        CriptografadorSenha.inserirNovoHash(c);

        return clienteDAO.inserir(c);
    }

    public List<Cliente> consultarTodos() throws Exception {
        return clienteDAO.consultarTodos();
    }

    public Cliente consultarByID(int id) throws Exception {
        return clienteDAO.consultarByID(id);
    }

    public Cliente atualizar(Cliente c) throws Exception {
        clienteValidador.validar(c);
        return clienteDAO.atualizar(c);
    }

    public Cliente atualizarSenha(Cliente c) throws Exception {
        CriptografadorSenha.inserirNovoHash(c);
        return clienteDAO.atualizarSenha(c);
    }

    public void deletar(Cliente c) throws Exception {
        clienteDAO.deletar(c);
    }

}
