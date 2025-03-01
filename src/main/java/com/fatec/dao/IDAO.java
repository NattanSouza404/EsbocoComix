package com.fatec.dao;

import java.util.List;

public interface IDAO <E> {
    public E inserir(E e) throws Exception;
    public List<E> consultarTodos() throws Exception;
    public E consultarByID(int id) throws Exception;
    public E atualizar(E e) throws Exception;
    public void deletar(E e) throws Exception;
}
