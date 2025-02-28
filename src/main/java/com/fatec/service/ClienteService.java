package com.fatec.service;

import java.util.List;

import com.fatec.dao.impl.ClienteDAO;
import com.fatec.model.entidades.Cliente;

public class ClienteService {
    private ClienteDAO clienteDAO = new ClienteDAO();
    private CartaoCreditoService cartaoCreditoService = new CartaoCreditoService();
    private ClienteValidador clienteValidador = new ClienteValidador();

    public void inserir(Cliente c) throws Exception {
        if (c.getSenha().length() < 8 || c.getSenha().length() > 64){
            throw new Exception("Senha deve ter entre 8 e 64 caracteres!");
        }

        clienteValidador.validar(c);

        String saltSenha = CriptografadorSenha.generateSalt();
        c.setHashSenha(CriptografadorSenha.hashSenha(c.getSenha(), saltSenha));
        c.setSaltSenha(saltSenha);
        
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
        if (c.getSenha().length() < 8 || c.getSenha().length() > 64){
            throw new Exception("Senha deve ter entre 8 e 64 caracteres!");
        }

        return clienteDAO.atualizar(c);
    }

    public void deletar(Cliente c) throws Exception {
        clienteDAO.deletar(c);
    }

}
