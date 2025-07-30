package com.esboco_comix.controller.impl;

import com.esboco_comix.controller.utils.AbstractController;
import com.esboco_comix.dto.AtualizarPedidoPosVendaDTO;
import com.esboco_comix.model.entidades.PedidoPosVenda;
import com.esboco_comix.service.impl.PedidoPosVendaService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class PedidoPosVendaController extends AbstractController {
    private final PedidoPosVendaService pedidoPosVendaService = new PedidoPosVendaService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String parametroId = req.getParameter("idcliente");
            Object objetoResposta;

            if (parametroId != null){
                int id = Integer.parseInt(parametroId);
                objetoResposta = pedidoPosVendaService.consultarPorIDCliente(id);
            } else {
                objetoResposta = pedidoPosVendaService.consultarTodos();
            }

            retornarRespostaJson(
                resp, 
                objetoResposta,
                HttpServletResponse.SC_OK
            );

        } catch (Exception e) {
            estourarErro(resp, new Exception("Erro ao consultar pedido(s)-pós-venda", e));
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            PedidoPosVenda pedido = jsonToObject(req, PedidoPosVenda.class);
            retornarRespostaJson(
                    resp,
                    pedidoPosVendaService.inserir(pedido),
                    HttpServletResponse.SC_CREATED
            );

        } catch (Exception e) {
            estourarErro(resp, new Exception("Erro ao adicionar pedido-pós-venda", e));
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            AtualizarPedidoPosVendaDTO pedido = jsonToObject(req, AtualizarPedidoPosVendaDTO.class);

            retornarRespostaJson(
                resp,
                pedidoPosVendaService.atualizarStatus(pedido),
                HttpServletResponse.SC_OK
            );

        } catch (Exception e) {
            estourarErro(resp, new Exception("Erro ao atualizar pedido-pós-venda", e));
        }
    }
}
