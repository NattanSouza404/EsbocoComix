package com.fatec.poo_crud_2sem_2024.dao;

import java.util.List;

import com.fatec.poo_crud_2sem_2024.modelo.entidades.Cliente;

public interface IClienteDAO {
    public void inserir(Cliente c) throws Exception;
    public List<Cliente> consultarTodos() throws Exception;
    public Cliente consultarByID(int id) throws Exception;
    public void atualizar(Cliente c) throws Exception;
    public void deletar(Cliente c) throws Exception;
}
