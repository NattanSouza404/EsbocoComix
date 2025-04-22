package com.esboco_comix.controller.impl;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.esboco_comix.controller.utils.AbstractController;
import com.esboco_comix.dto.ItemPedidoDTO;
import com.esboco_comix.dto.PedidoDTO;
import com.esboco_comix.model.entidades.Pedido;
import com.esboco_comix.service.impl.pedido.PedidoService;

public class PedidoController extends AbstractController {

    private PedidoService pedidoService = new PedidoService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            String parametroId = req.getParameter("idcliente");
            Object objetoResposta = null;

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
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        try {

            String opcao = Optional.ofNullable(req.getParameter("opcao")).orElse("default");
            Object objetoResposta = null;

            switch (opcao) {
                case "atualizarstatuspedido":
                    PedidoDTO pedido = jsonToObject(req, PedidoDTO.class);
                    objetoResposta = pedidoService.atualizarStatus(pedido);
                    break;
                
                case "atualizarstatusitem":
                    ItemPedidoDTO itemPedido = jsonToObject(req, ItemPedidoDTO.class);
                    objetoResposta = pedidoService.atualizarStatus(itemPedido);
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
