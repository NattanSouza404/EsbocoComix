package com.esboco_comix.quadrinho.service;

import java.util.List;
import java.util.stream.Collectors;

import com.esboco_comix.quadrinho.dao.QuadrinhoDAO;
import com.esboco_comix.quadrinho.dto.CategoriaDTO;
import com.esboco_comix.quadrinho.dto.FiltrarQuadrinhoDTO;
import com.esboco_comix.quadrinho.dto.QuadrinhoDTO;
import com.esboco_comix.quadrinho.mapper.CategoriaMapper;

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
