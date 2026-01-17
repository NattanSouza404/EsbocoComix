package com.esboco_comix.controller.impl;

import com.esboco_comix.controller.utils.AbstractController;
import com.esboco_comix.controller.utils.Router;
import com.esboco_comix.model.entidades.EntradaEstoque;
import com.esboco_comix.service.impl.EstoqueService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Map;

public class EstoqueController extends AbstractController {
    private final EstoqueService estoqueService = new EstoqueService();

    private final Router rotasGet = new Router(
        Map.of(
            "", this::consultar
        )
    );

    private final Router rotasPost = new Router(
        Map.of(
            "", this::adicionar
        )
    );

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        processar(
            req,
            resp, 
            rotasGet, 
            HttpServletResponse.SC_OK,
            "Erro ao consultar estoque"
        );
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        processar(
            req,
            resp, 
            rotasPost, 
            HttpServletResponse.SC_CREATED,
            "Erro ao adicionar estoque"
        );
    }

    private Object consultar(HttpServletRequest req) throws Exception {
        return estoqueService.consultarEntradasEstoque();
    }

    private Object adicionar(HttpServletRequest req) throws Exception {
        EntradaEstoque entradaEstoque = jsonToObject(req, EntradaEstoque.class);
        return estoqueService.inserir(entradaEstoque);
    }
}
