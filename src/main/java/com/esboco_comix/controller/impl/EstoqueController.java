package com.esboco_comix.controller.impl;

import com.esboco_comix.controller.utils.AbstractController;
import com.esboco_comix.model.entidades.EntradaEstoque;
import com.esboco_comix.service.impl.EstoqueService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class EstoqueController extends AbstractController {
    private final EstoqueService estoqueService = new EstoqueService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
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
