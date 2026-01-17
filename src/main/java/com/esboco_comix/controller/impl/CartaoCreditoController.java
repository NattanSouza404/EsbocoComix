package com.esboco_comix.controller.impl;

import com.esboco_comix.controller.utils.AbstractController;
import com.esboco_comix.controller.utils.Router;
import com.esboco_comix.model.entidades.CartaoCredito;
import com.esboco_comix.service.impl.CartaoCreditoService;
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
        int id = Integer.parseInt(requireParam(req, "id"));
        return cartaoCreditoService.consultarByIDCliente(id);
    }

    private Object adicionar(HttpServletRequest req) throws Exception {
        CartaoCredito cartaoCreditoToAdd = jsonToObject(req, CartaoCredito.class);
        return cartaoCreditoService.inserir(cartaoCreditoToAdd);
    }

    private Object atualizar(HttpServletRequest req) throws Exception {
        CartaoCredito cartaoCredito = jsonToObject(req, CartaoCredito.class);   
        return cartaoCreditoService.atualizar(cartaoCredito);
    }

    private Object deletar(HttpServletRequest req) throws Exception {
        CartaoCredito cartaoCredito = jsonToObject(req, CartaoCredito.class);
        cartaoCreditoService.deletar(cartaoCredito);
        return null;
    }
}
