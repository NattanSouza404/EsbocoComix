package com.esboco_comix.controller.impl;

import com.esboco_comix.controller.utils.AbstractController;
import com.esboco_comix.controller.utils.Router;
import com.esboco_comix.dto.AtualizarPedidoDTO;
import com.esboco_comix.model.Carrinho;
import com.esboco_comix.model.entidades.Pedido;
import com.esboco_comix.service.impl.pedido.PedidoService;
import com.esboco_comix.sessao.SessaoService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Map;

public class PedidoController extends AbstractController {

    private final PedidoService pedidoService = new PedidoService();
    private final SessaoService sessaoService = new SessaoService();

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
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        processar(
            req,
            resp,
            rotasGet,
            HttpServletResponse.SC_OK,
            "Erro ao consultar pedido(s)"
        );
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        processar(
            req,
            resp,
            rotasPost,
            HttpServletResponse.SC_CREATED,
            "Erro ao adicionar pedido"
        );
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        processar(
            req,
            resp,
            rotasPut,
            HttpServletResponse.SC_OK,
            "Erro ao atualizar pedido"
        );
    }

    private Object consultarTodos(HttpServletRequest req) throws Exception {
        return pedidoService.consultarTodos();
    }

    private Object consultarPorIdCliente(HttpServletRequest req) throws Exception {
        int idCliente = Integer.parseInt(
            requireParam(req, "id")
        );

        return pedidoService.consultarPorIDCliente(idCliente);
    }

    private Object adicionar(HttpServletRequest req) throws Exception {
        Pedido pedido = jsonToObject(req, Pedido.class);   
        Carrinho carrinho = sessaoService.retornarCarrinho(req.getSession());
        return pedidoService.inserir(pedido, carrinho);
    }

    private Object atualizarStatus(HttpServletRequest req) throws Exception {
        AtualizarPedidoDTO pedido = jsonToObject(req, AtualizarPedidoDTO.class);
        return pedidoService.atualizarStatus(pedido);
    }
}
