package com.esboco_comix.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.esboco_comix.controller.utils.AbstractController;
import com.esboco_comix.model.entidades.Pedido;
import com.esboco_comix.service.PedidoService;

public class PedidoController extends AbstractController {

    private PedidoService pedidoService = new PedidoService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            retornarRespostaJson(
                resp, 
                pedidoService.consultarTodos(),
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

            retornarRespostaJson(
                resp,
                pedidoService.inserir(pedido),
                HttpServletResponse.SC_CREATED
            );
        } catch (Exception e) {
            estourarErro(resp, e);
        }
    }
}
