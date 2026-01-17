package com.esboco_comix.controller.impl;

import com.esboco_comix.controller.utils.AbstractController;
import com.esboco_comix.controller.utils.Router;
import com.esboco_comix.dto.ItemCarrinhoDTO;
import com.esboco_comix.service.impl.CarrinhoService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Map;

public class CarrinhoController extends AbstractController {

    private final CarrinhoService carrinhoService = new CarrinhoService();

    private final Router rotasGet = new Router(
        Map.of(
            "", this::consultarCarrinho
        )
    );

    private final Router rotasPost = new Router(
        Map.of(
            "/adicionar-item", this::adicionarItemCarrinho
        )
    );

    private final Router rotasPut = new Router(
        Map.of(
            "/atualizar-item", this::atualizarItemCarrinho
        )
    );

    private final Router rotasDelete = new Router(
        Map.of(
            "", this::deletarItemCarrinho
        )
    );

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        processar(
            req,
            resp,
            rotasGet,
            HttpServletResponse.SC_OK,
            "Erro ao buscar carrinho"
        );
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        processar(
            req,
            resp,
            rotasPost,
            HttpServletResponse.SC_CREATED,
            "Erro ao adicionar item ao carrinho"
        );
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        processar(
            req,
            resp,
            rotasPut,
            HttpServletResponse.SC_OK,
            "Erro ao adicionar atualizar item do carrinho"
        );
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        processar(
            req,
            resp,
            rotasDelete,
            HttpServletResponse.SC_NO_CONTENT,
            "Erro ao deletar item do carrinho"
        );
    }

    private Object consultarCarrinho(HttpServletRequest req) throws Exception {
        return carrinhoService.retornarCarrinhoSessao(req.getSession());
    }

    private Object adicionarItemCarrinho(HttpServletRequest req) throws Exception {
        ItemCarrinhoDTO itemCarrinho = jsonToObject(req, ItemCarrinhoDTO.class); 
        return carrinhoService.adicionar(itemCarrinho, req.getSession());
    }

    private Object atualizarItemCarrinho(HttpServletRequest req) throws Exception {
        ItemCarrinhoDTO itemCarrinho = jsonToObject(req, ItemCarrinhoDTO.class); 
        return carrinhoService.atualizarQuantidade(itemCarrinho, req.getSession());
    }

    private Object deletarItemCarrinho(HttpServletRequest req) throws Exception {
        ItemCarrinhoDTO itemCarrinho = jsonToObject(req, ItemCarrinhoDTO.class);
        carrinhoService.deletar(itemCarrinho, req.getSession());
        return null;
    }

}
