package com.esboco_comix.controller.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.esboco_comix.controller.utils.AbstractController;
import com.esboco_comix.model.entidades.EntradaEstoque;
import com.esboco_comix.service.impl.EstoqueService;

public class EstoqueController extends AbstractController {
    private EstoqueService estoqueService = new EstoqueService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            EntradaEstoque entradaEstoque = jsonToObject(req, EntradaEstoque.class);
            retornarRespostaJson(
                resp,
                estoqueService.inserir(entradaEstoque),
                HttpServletResponse.SC_CREATED
            );
        } catch (Exception e) {
            estourarErro(resp, e);
        }
    }
}
