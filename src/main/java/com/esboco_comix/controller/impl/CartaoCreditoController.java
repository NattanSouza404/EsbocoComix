package com.esboco_comix.controller.impl;

import com.esboco_comix.controller.utils.AbstractController;
import com.esboco_comix.model.entidades.CartaoCredito;
import com.esboco_comix.service.impl.CartaoCreditoService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class CartaoCreditoController extends AbstractController {

    private final CartaoCreditoService cartaoCreditoService = new CartaoCreditoService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {

            String parametroIdCliente = req.getParameter("idcliente");

            if (parametroIdCliente != null){
                int id = Integer.parseInt(parametroIdCliente);

                retornarRespostaJson(
                    resp,
                    cartaoCreditoService.consultarByIDCliente(id),
                    HttpServletResponse.SC_OK
                );

            }

        } catch (Exception e) {
            estourarErro(resp, e);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            CartaoCredito cartaoCreditoToAdd = jsonToObject(req, CartaoCredito.class);
            retornarRespostaJson(
                resp,
                cartaoCreditoService.inserir(cartaoCreditoToAdd),
                HttpServletResponse.SC_CREATED
            );
        } catch (Exception e) {
            estourarErro(resp, e);
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        
        try {
            CartaoCredito cartaoCredito = jsonToObject(req, CartaoCredito.class);
            retornarRespostaJson(
                resp,
                cartaoCreditoService.atualizar(cartaoCredito),
                HttpServletResponse.SC_OK
            );
        } catch (Exception e) {
            estourarErro(resp, e);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            CartaoCredito cartaoCredito = jsonToObject(req, CartaoCredito.class);
            cartaoCreditoService.deletar(cartaoCredito);
            resp.setStatus(HttpServletResponse.SC_NO_CONTENT);
        } catch (Exception e) {
            estourarErro(resp, e);
        }
    }
}
