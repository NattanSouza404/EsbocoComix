package com.esboco_comix.controller.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.esboco_comix.controller.utils.AbstractController;
import com.esboco_comix.dto.ItemCarrinhoDTO;
import com.esboco_comix.service.impl.CarrinhoService;

public class CarrinhoController extends AbstractController {

    private CarrinhoService carrinhoService = new CarrinhoService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

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
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

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
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            ItemCarrinhoDTO itemCarrinho = jsonToObject(req, ItemCarrinhoDTO.class);

            carrinhoService.deletar(itemCarrinho, getSession(req));

            resp.setStatus(HttpServletResponse.SC_NO_CONTENT);
        } catch (Exception e) {
            estourarErro(resp, e);
        }
    }
}
