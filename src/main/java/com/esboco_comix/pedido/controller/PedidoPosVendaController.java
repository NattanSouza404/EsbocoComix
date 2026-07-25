package com.esboco_comix.pedido.controller;

import com.esboco_comix.core.controller.utils.AbstractController;
import com.esboco_comix.core.controller.utils.Router;
import com.esboco_comix.pedido.dominio.PedidoPosVenda;
import com.esboco_comix.pedido.dto.AtualizarPedidoPosVendaDTO;
import com.esboco_comix.pedido.service.PedidoPosVendaService;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Map;

public class PedidoPosVendaController extends AbstractController {
    private final PedidoPosVendaService pedidoPosVendaService = new PedidoPosVendaService();

    private final Router rotasGet = new Router(
        Map.of(
            "", this::consultarTodos,
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
            "/atualizar-status", this::atualizarStatus
        )
    );

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processar(
            req,
            resp,
            rotasGet,
            HttpServletResponse.SC_OK,
            "Erro ao consultar pedido(s)-pós-venda"
        );
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processar(
            req,
            resp,
            rotasPost,
            HttpServletResponse.SC_CREATED,
            "Erro ao adicionar pedido-pós-venda"
        );
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        processar(
            req,
            resp,
            rotasPut,
            HttpServletResponse.SC_OK,
            "Erro ao atualizar pedido-pós-venda"
        );
        
    }

    private Object consultarTodos(HttpServletRequest req) throws Exception {
        return pedidoPosVendaService.consultarTodos();
    }

    private Object consultarPorIdCliente(HttpServletRequest req) throws Exception {
        int id = Integer.parseInt(
            requireParam(req, "id")
        );

        return pedidoPosVendaService.consultarPorIDCliente(id);
    }

    private Object adicionar(HttpServletRequest req) throws Exception {
        PedidoPosVenda pedido = jsonToObject(req, PedidoPosVenda.class);
        return pedidoPosVendaService.inserir(pedido);
    }

    private Object atualizarStatus(HttpServletRequest req) throws Exception {
        AtualizarPedidoPosVendaDTO pedido = jsonToObject(req, AtualizarPedidoPosVendaDTO.class);
        return pedidoPosVendaService.atualizarStatus(pedido);
    }
}
