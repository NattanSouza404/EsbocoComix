package com.fatec.service;

import java.util.List;

import com.fatec.dao.impl.ClienteDAO;
import com.fatec.dao.impl.EnderecoDAO;
import com.fatec.model.entidades.Cliente;
import com.fatec.model.entidades.Endereco;
import com.fatec.utils.CriptografadorSenha;

public class ClienteService {
    private ClienteDAO clienteDAO = new ClienteDAO();
    private EnderecoDAO enderecoDAO = new EnderecoDAO();

    private ClienteValidador clienteValidador = new ClienteValidador();

    public Cliente inserir(Cliente c) throws Exception {
        clienteValidador.validar(c);
        inserirNovoHash(c);
        return clienteDAO.inserir(c);
    }

    public List<Cliente> consultarTodos() throws Exception {
        List<Cliente> clientes = clienteDAO.consultarTodos();

        for (Cliente c: clientes) {
            try {
                c.setEnderecos(enderecoDAO.consultarByIDCliente(c.getId()));
            } catch (Exception e){
                System.err.println(e);
            }
        }

        return clientes;
    }

    public Cliente consultarByID(int id) throws Exception {
        Cliente cliente = consultarByID(id);
        cliente.setEnderecos(enderecoDAO.consultarByIDCliente(id));
        return cliente;
    }

    public Cliente atualizar(Cliente c) throws Exception {
        return clienteDAO.atualizar(c);
    }

    public Cliente atualizarSenha(Cliente c) throws Exception {
        clienteValidador.validar(c);

        Cliente clienteInserido = clienteDAO.consultarHashSaltPorID(c.getId()); 
        String hashGuardado = clienteInserido.getHashSenha();
        String saltGuardado = clienteInserido.getSaltSenha();

        validarSenha(c.getSenha(), hashGuardado, saltGuardado);
        inserirNovoHash(c);
        return clienteDAO.atualizarSenha(c);
    }

    public void deletar(Cliente c) throws Exception {
        clienteDAO.deletar(c);
        for (Endereco e : c.getEnderecos()) {
            enderecoDAO.deletar(e);
        }
    }

    private void validarSenha(String senha, String hash, String salt) throws Exception {
        String hashNovo = CriptografadorSenha.hashSenha(senha, salt); 
        if (!hashNovo.equals(hash)){
            throw new Exception("Senha antiga não consta com senha inserida pelo usuário!");
        }
    }

    private void inserirNovoHash(Cliente c) throws Exception {
        String saltSenha = CriptografadorSenha.generateSalt();
        c.setHashSenha(CriptografadorSenha.hashSenha(c.getSenha(), saltSenha));
        c.setSaltSenha(saltSenha);
    }

}
