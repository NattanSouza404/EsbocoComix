package com.esboco_comix.controller.impl;

import com.esboco_comix.controller.utils.AbstractController;
import com.esboco_comix.dto.ItemCarrinhoDTO;
import com.esboco_comix.service.impl.CarrinhoService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class CarrinhoController extends AbstractController {

    private final CarrinhoService carrinhoService = new CarrinhoService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        try {
            retornarRespostaJson(
                resp, 
                carrinhoService.retornarCarrinhoSessao(getSession(req)),
                HttpServletResponse.SC_OK
            ); 

        } catch (Exception e) {
            estourarErro(resp, e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            ItemCarrinhoDTO itemCarrinho = jsonToObject(req, ItemCarrinhoDTO.class); 

            retornarRespostaJson(
                resp, 
                carrinhoService.adicionar(itemCarrinho, getSession(req)),
                HttpServletResponse.SC_CREATED
            ); 

        } catch (Exception e) {
            estourarErro(resp, e);
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        try {
            ItemCarrinhoDTO itemCarrinho = jsonToObject(req, ItemCarrinhoDTO.class); 

            retornarRespostaJson(
                resp, 
                carrinhoService.atualizarQuantidade(itemCarrinho, getSession(req)),
                HttpServletResponse.SC_OK
            ); 

        } catch (Exception e) {
            estourarErro(resp, e);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            ItemCarrinhoDTO itemCarrinho = jsonToObject(req, ItemCarrinhoDTO.class);

            carrinhoService.deletar(itemCarrinho, getSession(req));

            resp.setStatus(HttpServletResponse.SC_NO_CONTENT);
        } catch (Exception e) {
            estourarErro(resp, e);
        }
    }
}
