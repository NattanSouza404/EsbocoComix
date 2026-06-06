package com.esboco_comix.controller.impl;

import com.esboco_comix.controller.utils.AbstractController;
import com.esboco_comix.controller.utils.Router;
import com.esboco_comix.model.entidades.Cupom;
import com.esboco_comix.service.impl.CupomService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Map;

public class CupomController extends AbstractController {

    private final CupomService cupomService = new CupomService();

    private final Router rotasGet = new Router(Map.of(
        "/por-id-cliente", this::consultarPorIdCliente
    ));

    private final Router rotasPost = new Router(Map.of(
        "", this::adicionar
    ));

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        processar(
            req,
            resp, 
            rotasGet, 
            HttpServletResponse.SC_OK,
            "Erro ao consultar cupom(ns)"
        );
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        processar(
            req,
            resp,
            rotasPost,
            HttpServletResponse.SC_CREATED,
            "Erro ao adicionar cupom"
        );
    }

    private Object consultarPorIdCliente(HttpServletRequest req) throws Exception {
        int idCliente = Integer.parseInt(
            requireParam(req, "id")
        );

        return cupomService.consultarByIDCliente(idCliente);
    }

    private Object adicionar(HttpServletRequest req) throws Exception {
        Cupom cupom = jsonToObject(req, Cupom.class);

        return cupomService.inserir(cupom);
    }
}
