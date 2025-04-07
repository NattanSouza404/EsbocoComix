package com.esboco_comix.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.esboco_comix.model.Carrinho;
import com.esboco_comix.model.ItemCarrinho;
import com.esboco_comix.service.CarrinhoService;

import static com.esboco_comix.controller.utils.ServletUtil.*;

public class CarrinhoController extends HttpServlet {

    private CarrinhoService carrinhoService = new CarrinhoService();

    private SessionManager sessionManager = new SessionManager();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            HttpSession session = sessionManager.retornarSessao(req);

            retornarRespostaJson(
                resp, 
                session.getAttribute("carrinho"),
                HttpServletResponse.SC_OK
            ); 

        } catch (Exception e) {
            estourarErro(resp, e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            HttpSession session = sessionManager.retornarSessao(req);
            Carrinho carrinho = sessionManager.retornarCarrinhoSessao();
            ItemCarrinho itemCarrinho = jsonToObject(req, ItemCarrinho.class); 

            carrinhoService.adicionar(itemCarrinho, carrinho);

            session.setAttribute("carrinho", carrinho);

            retornarRespostaJson(
                resp, 
                session.getAttribute("carrinho"),
                HttpServletResponse.SC_CREATED
            ); 

        } catch (Exception e) {
            estourarErro(resp, e);
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            HttpSession session = sessionManager.retornarSessao(req);
            Carrinho carrinho = sessionManager.retornarCarrinhoSessao();
            ItemCarrinho itemCarrinho = jsonToObject(req, ItemCarrinho.class); 

            carrinhoService.atualizarQuantidade(itemCarrinho, carrinho);

            session.setAttribute("carrinho", carrinho);

            retornarRespostaJson(
                resp, 
                session.getAttribute("carrinho"),
                HttpServletResponse.SC_OK
            ); 

        } catch (Exception e) {
            estourarErro(resp, e);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            HttpSession session = sessionManager.retornarSessao(req);
            Carrinho carrinho = sessionManager.retornarCarrinhoSessao();

            ItemCarrinho itemCarrinho = jsonToObject(req, ItemCarrinho.class);

            carrinhoService.deletar(itemCarrinho, carrinho);

            resp.setStatus(HttpServletResponse.SC_NO_CONTENT);
        } catch (Exception e) {
            estourarErro(resp, e);
        }
    }
}
