package com.esboco_comix.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.esboco_comix.controller.utils.AbstractController;
import com.esboco_comix.model.Carrinho;
import com.esboco_comix.model.ItemCarrinho;
import com.esboco_comix.service.CarrinhoService;

public class CarrinhoController extends AbstractController {

    private CarrinhoService carrinhoService = new CarrinhoService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            retornarRespostaJson(
                resp, 
                retornarCarrinhoSessao(req),
                HttpServletResponse.SC_OK
            ); 

        } catch (Exception e) {
            estourarErro(resp, e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            ItemCarrinho itemCarrinho = jsonToObject(req, ItemCarrinho.class); 
            Carrinho carrinho = retornarCarrinhoSessao(req);

            carrinhoService.adicionar(itemCarrinho, carrinho);

            retornarRespostaJson(
                resp, 
                carrinho,
                HttpServletResponse.SC_CREATED
            ); 

        } catch (Exception e) {
            estourarErro(resp, e);
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            Carrinho carrinho = retornarCarrinhoSessao(req);
            ItemCarrinho itemCarrinho = jsonToObject(req, ItemCarrinho.class); 

            carrinhoService.atualizarQuantidade(itemCarrinho, carrinho);

            retornarRespostaJson(
                resp, 
                carrinho,
                HttpServletResponse.SC_OK
            ); 

        } catch (Exception e) {
            estourarErro(resp, e);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Carrinho carrinho = retornarCarrinhoSessao(req);
            ItemCarrinho itemCarrinho = jsonToObject(req, ItemCarrinho.class);

            carrinhoService.deletar(itemCarrinho, carrinho);

            resp.setStatus(HttpServletResponse.SC_NO_CONTENT);
        } catch (Exception e) {
            estourarErro(resp, e);
        }
    }
}
