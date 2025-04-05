package com.esboco_comix.service;

import com.esboco_comix.dao.impl.QuadrinhoDAO;
import com.esboco_comix.model.entidades.Quadrinho;

public class QuadrinhoService {

    private QuadrinhoDAO quadrinhoDAO = new QuadrinhoDAO();

    public Quadrinho consultarByID(int id) throws Exception {
        return quadrinhoDAO.consultarByID(id);
    }

    public Object consultarTodos() throws Exception {
        return quadrinhoDAO.consultarTodos();
    }
    
}
