package com.fatec.dao;

import java.util.List;

import com.fatec.modelo.entidades.Cliente;

public interface IClienteDAO {
    public void inserir(Cliente c) throws Exception;
    public List<Cliente> consultarTodos() throws Exception;
    public Cliente consultarByID(int id) throws Exception;
    public void atualizar(Cliente c) throws Exception;
    public void deletar(Cliente c) throws Exception;
}
