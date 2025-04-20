package com.esboco_comix.controller.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.esboco_comix.controller.utils.AbstractController;
import com.esboco_comix.model.entidades.ItemPedido;
import com.esboco_comix.service.impl.PedidoTrocaService;

public class PedidoTrocaController extends AbstractController {

    private PedidoTrocaService pedidoTrocaService = new PedidoTrocaService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            retornarRespostaJson(
                resp, 
                pedidoTrocaService.consultarPedidosTroca(),
                HttpServletResponse.SC_OK
            ); 

        } catch (Exception e) {
            estourarErro(resp, e);
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        try {
            ItemPedido pedido = jsonToObject(req, ItemPedido.class);

            if (req.getParameter("opcao").equals("atualizarstatus")){
                retornarRespostaJson(
                    resp,
                    pedidoTrocaService.atualizarStatus(pedido),
                    HttpServletResponse.SC_OK
                );
            }

            
        } catch (Exception e) {
            estourarErro(resp, e);
        }
    }
}
