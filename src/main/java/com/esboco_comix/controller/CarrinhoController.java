package com.esboco_comix.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

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

    private HttpSession session;

    private CarrinhoService carrinhoService = new CarrinhoService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            iniciarSessao(req);

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
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            iniciarSessao(req);

            String opcao = Optional.ofNullable(req.getParameter("opcao")).orElse("default");
            Carrinho carrinho = (Carrinho) session.getAttribute("carrinho");    
            ItemCarrinho itemCarrinho = jsonToObject(req, ItemCarrinho.class); 

            switch (opcao) {
                case "adicionar": 
                    carrinhoService.adicionar(itemCarrinho, carrinho);
                    break;

                case "atualizarquantidade":
                    carrinhoService.atualizarQuantidade(itemCarrinho, carrinho);
                    break;
            }

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

    private void iniciarSessao(HttpServletRequest req) {
        if (session != null){
            return;
        }

        session = req.getSession();

        Carrinho carrinho = (Carrinho) session.getAttribute("carrinho");
    
        if (carrinho == null){
            carrinho = new Carrinho();
            carrinho.setItensCarrinho(new ArrayList<>());
        }

        session.setAttribute("carrinho", carrinho);
    }
}
