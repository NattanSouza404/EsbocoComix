package com.esboco_comix.service.impl;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import com.esboco_comix.dao.impl.quadrinho.QuadrinhoDAO;
import com.esboco_comix.dto.FiltrarQuadrinhoDTO;
import com.esboco_comix.dto.QuadrinhoDTO;
import com.esboco_comix.model.entidades.Categoria;

import jakarta.servlet.http.HttpServletRequest;

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

    public List<QuadrinhoDTO> filtrarTodos(HttpServletRequest req) {
        FiltrarQuadrinhoDTO filtro = new FiltrarQuadrinhoDTO();

        String titulo = req.getParameter("titulo");
        if (!titulo.isBlank()){
            filtro.setTitulo(titulo);
        }

        String autor = req.getParameter("autor");
        if (!autor.isBlank()){
            filtro.setAutor(autor);
        }

        String ano = req.getParameter("ano");
        if (!ano.isBlank()){
            filtro.setAno(LocalDate.of(Integer.parseInt(ano), 0, 0));
        }

        String numeroPaginas = req.getParameter("numeroPaginas");
        if (!numeroPaginas.isBlank()){
            filtro.setNumeroPaginas(Integer.parseInt(numeroPaginas));
        }

        String codigoBarras = req.getParameter("codigoBarras");
        if (!codigoBarras.isBlank()){
            filtro.setCodigoBarras(codigoBarras);
        }

        String edicao = req.getParameter("edicao");
        if (!edicao.isBlank()){
            filtro.setEdicao(Integer.parseInt(edicao));
        }

        String editora = req.getParameter("editora");
        if (!editora.isBlank()){
            filtro.setEditora(editora);
        }

        String isbn = req.getParameter("isbn");
        if (!isbn.isBlank()){
            filtro.setIsbn(isbn);
        }

        String altura = req.getParameter("altura");
        if (!altura.isBlank()){
            filtro.setAltura(Integer.parseInt(altura));
        }

        String largura = req.getParameter("largura");
        if (!largura.isBlank()){
            filtro.setLargura(Integer.parseInt(largura));
        }

        String profundidade = req.getParameter("profundidade");
        if (!profundidade.isBlank()){
            filtro.setProfundidade(Integer.parseInt(profundidade));
        }

        String peso = req.getParameter("peso");
        if (!peso.isBlank()){
            filtro.setPeso(Integer.parseInt(peso));
        }

        String grupoPrecificacao = req.getParameter("grupoPrecificacao");
        if (!grupoPrecificacao.isBlank()){
            filtro.setGrupoPrecificacao(grupoPrecificacao);
        }

        String categorias = req.getParameter("categorias");
        if (!categorias.isBlank()){
            String[] listaCategorias = categorias.split(",");
            filtro.setCategorias(Arrays.asList(listaCategorias));
        }

        return quadrinhoDAO.filtrarTodos(filtro);
    }
    
}
