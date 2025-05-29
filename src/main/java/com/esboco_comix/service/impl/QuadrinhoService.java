package com.esboco_comix.service.impl;

import java.util.List;

import com.esboco_comix.dao.impl.quadrinho.QuadrinhoDAO;
import com.esboco_comix.dto.FiltrarQuadrinhoDTO;
import com.esboco_comix.dto.QuadrinhoDTO;
import com.esboco_comix.model.entidades.Quadrinho;

import jakarta.servlet.http.HttpServletRequest;

public class QuadrinhoService {

    private final QuadrinhoDAO quadrinhoDAO = new QuadrinhoDAO();

    public QuadrinhoDTO consultarByID(int id) throws Exception {
        return quadrinhoDAO.consultarByID(id);
    }

    public List<QuadrinhoDTO> consultarTodos() throws Exception {
        return quadrinhoDAO.consultarTodos();
    }

    public List<Quadrinho> filtrarTodos(HttpServletRequest req) throws Exception {
        FiltrarQuadrinhoDTO filtro = new FiltrarQuadrinhoDTO();

        String titulo = req.getParameter("titulo");
        if (!titulo.isBlank()){
            filtro.setTitulo(titulo);
        }

        String autor = req.getParameter("autor");
        if (!autor.isBlank()){
            filtro.setAutor(autor);
        }

        return quadrinhoDAO.filtrarTodos(filtro);
    }
    
}
