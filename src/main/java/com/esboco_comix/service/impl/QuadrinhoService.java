package com.esboco_comix.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import com.esboco_comix.dao.impl.quadrinho.QuadrinhoDAO;
import com.esboco_comix.dto.CategoriaDTO;
import com.esboco_comix.dto.FiltrarQuadrinhoDTO;
import com.esboco_comix.dto.QuadrinhoDTO;
import com.esboco_comix.mapper.CategoriaMapper;

public class QuadrinhoService {

    private final QuadrinhoDAO quadrinhoDAO = new QuadrinhoDAO();

    private final CategoriaMapper categoriaMapper = new CategoriaMapper();

    public QuadrinhoDTO consultarByID(int id) {
        return quadrinhoDAO.consultarByID(id);
    }

    public List<QuadrinhoDTO> consultarTodos() {
        return quadrinhoDAO.consultarTodos();
    }

    public List<CategoriaDTO> consultarTodasCategorias() {     
        return quadrinhoDAO.consultarTodasCategorias().stream()
            .map(categoriaMapper::toDTO)
        .collect(Collectors.toList());
    }

    public List<QuadrinhoDTO> filtrarTodos(FiltrarQuadrinhoDTO filtro) {
        return quadrinhoDAO.filtrarTodos(filtro);
    }
    
}
