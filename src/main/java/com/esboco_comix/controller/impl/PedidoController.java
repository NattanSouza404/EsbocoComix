package com.esboco_comix.controller.impl;

import com.esboco_comix.controller.utils.AbstractController;
import com.esboco_comix.dto.AtualizarPedidoDTO;
import com.esboco_comix.model.entidades.Pedido;
import com.esboco_comix.service.impl.pedido.PedidoService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Optional;

public class PedidoController extends AbstractController {

    private final PedidoService pedidoService = new PedidoService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        try {
            String parametroId = req.getParameter("idcliente");
            Object objetoResposta;

            if (parametroId != null){
                int id = Integer.parseInt(parametroId);
                objetoResposta = pedidoService.consultarPorIDCliente(id);
            } else {
                objetoResposta = pedidoService.consultarTodos();
            }

            retornarRespostaJson(
                resp, 
                objetoResposta,
                HttpServletResponse.SC_OK
            ); 

        } catch (Exception e) {
            estourarErro(resp, e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            Pedido pedido = jsonToObject(req, Pedido.class);
            
            Pedido pedidoInserido = pedidoService.inserir(pedido, getSession(req));

            retornarRespostaJson(
                resp,
                pedidoInserido,
                HttpServletResponse.SC_CREATED
            );

        } catch (Exception e) {
            estourarErro(resp, e);
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        
        try {

            String opcao = Optional.ofNullable(req.getParameter("opcao")).orElse("default");
            Object objetoResposta = null;

            switch (opcao) {
                case "atualizarstatuspedido":
                    AtualizarPedidoDTO pedido = jsonToObject(req, AtualizarPedidoDTO.class);
                    objetoResposta = pedidoService.atualizarStatus(pedido);
                    break;
            }

            retornarRespostaJson(
                resp,
                objetoResposta,
                HttpServletResponse.SC_OK
            );

        } catch (Exception e) {
            estourarErro(resp, e);
        }
    }
}
