package com.esboco_comix.controller.impl;

import com.esboco_comix.controller.utils.AbstractController;
import com.esboco_comix.controller.utils.Router;
import com.esboco_comix.service.impl.QuadrinhoService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Map;

public class QuadrinhoController extends AbstractController {

    private final QuadrinhoService quadrinhoService = new QuadrinhoService();

    private final Router rotasGet = new Router(
        Map.of(
            "", this::consultarTodos,
            "/id", this::consultarPorId,
            "/filtrar", this::filtrar,
            "/consultar-categorias", this::consultarCategorias
        )
    );

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        processar(
            req,
            resp,
            rotasGet,
            HttpServletResponse.SC_OK,
            "Erro ao consultar quadrinho(s) / categoria(s)"
        );
    }

    private Object consultarTodos(HttpServletRequest req) throws Exception {
        return quadrinhoService.consultarTodos();
    }

    private Object consultarPorId(HttpServletRequest req) throws Exception {
        int id = Integer.parseInt(
            requireParam(req, "id")
        );
        
        return quadrinhoService.consultarByID(id);
    }

    private Object filtrar(HttpServletRequest req) throws Exception {
        return quadrinhoService.filtrarTodos(req);
    }

    private Object consultarCategorias(HttpServletRequest req) throws Exception {
        return quadrinhoService.consultarTodasCategorias();
    }

}
