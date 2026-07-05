package com.esboco_comix.service.impl;

import java.util.List;

import com.esboco_comix.dao.impl.quadrinho.QuadrinhoDAO;
import com.esboco_comix.dto.FiltrarQuadrinhoDTO;
import com.esboco_comix.dto.QuadrinhoDTO;
import com.esboco_comix.model.entidades.Categoria;

public class QuadrinhoService {

    private final QuadrinhoDAO quadrinhoDAO = new QuadrinhoDAO();

    public QuadrinhoDTO consultarByID(int id) {
        return quadrinhoDAO.consultarByID(id);
    }

    public List<QuadrinhoDTO> consultarTodos() {
        return quadrinhoDAO.consultarTodos();
    }

    public List<Categoria> consultarTodasCategorias() {
        return quadrinhoDAO.consultarTodasCategorias();
    }

    public List<QuadrinhoDTO> filtrarTodos(FiltrarQuadrinhoDTO filtro) {
        return quadrinhoDAO.filtrarTodos(filtro);
    }
    
}
