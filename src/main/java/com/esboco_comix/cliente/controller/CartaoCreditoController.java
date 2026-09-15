package com.esboco_comix.cliente.controller;

import com.esboco_comix.cliente.dominio.entidades.CartaoCredito;
import com.esboco_comix.cliente.service.CartaoCreditoService;
import com.esboco_comix.core.controller.AbstractController;
import com.esboco_comix.core.routing.Router;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Map;

public class CartaoCreditoController extends AbstractController {

    private final CartaoCreditoService cartaoCreditoService = new CartaoCreditoService();

    private final Router rotasGet = new Router(
        Map.of(
            "/por-id-cliente", this::consultarPorIdCliente
        )
    );

    private final Router rotasPost = new Router(
        Map.of(
            "", this::adicionar
        )
    );

    private final Router rotasPut = new Router(
        Map.of(
            "", this::atualizar
        )
    );

    private final Router rotasDelete = new Router(
        Map.of(
            "", this::deletar
        )
    );

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        processar(req,
            resp,
            rotasGet,
            HttpServletResponse.SC_OK,
            "Erro ao consultar cartões de crédito"
        );
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        processar(
            req,
            resp,
            rotasPost,
            HttpServletResponse.SC_CREATED,
            "Erro ao adicionar cartão de crédito"
        );
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        processar(
            req,
            resp,
            rotasPut,
            HttpServletResponse.SC_OK,
            "Erro ao atualizar cartão de crédito"
        );
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        processar(
            req,
            resp,
            rotasDelete,
            HttpServletResponse.SC_NO_CONTENT,
            "Erro ao deletar cartão de crédito"
        );
    }

    private Object consultarPorIdCliente(HttpServletRequest req) throws Exception {
        return cartaoCreditoService.consultarByIDCliente(
            Integer.parseInt(requireParam(req, "id"))
        );
    }

    private Object adicionar(HttpServletRequest req) throws Exception {
        return cartaoCreditoService.inserir(
            jsonToObject(req, CartaoCredito.class)
        );
    }

    private Object atualizar(HttpServletRequest req) throws Exception {
        return cartaoCreditoService.atualizar(
            jsonToObject(req, CartaoCredito.class)
        );
    }

    private Object deletar(HttpServletRequest req) throws Exception {
        cartaoCreditoService.deletar(
            jsonToObject(req, CartaoCredito.class)
        );

        return null;
    }
}
