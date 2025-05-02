package com.esboco_comix.service.impl;

import java.util.List;

import com.esboco_comix.dao.impl.quadrinho.QuadrinhoDAO;
import com.esboco_comix.dto.QuadrinhoDTO;

public class QuadrinhoService {

    private QuadrinhoDAO quadrinhoDAO = new QuadrinhoDAO();

    public QuadrinhoDTO consultarByID(int id) throws Exception {
        return quadrinhoDAO.consultarByID(id);
    }

    public List<QuadrinhoDTO> consultarTodos() throws Exception {
        return quadrinhoDAO.consultarTodos();
    }
    
}
