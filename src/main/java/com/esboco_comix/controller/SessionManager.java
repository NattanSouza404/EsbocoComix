package com.esboco_comix.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.esboco_comix.model.Carrinho;
import com.esboco_comix.model.entidades.Cliente;

public class SessionManager {
    private HttpSession session;

    public HttpSession retornarSessao(HttpServletRequest req) {
        this.session = req.getSession();
        return this.session;
    }

    public Carrinho retornarCarrinhoSessao(){
        Carrinho carrinho = (Carrinho) session.getAttribute("carrinho");

        if (carrinho == null) {
            carrinho = new Carrinho();
            session.setAttribute("carrinho", carrinho);
        }

        return carrinho;   
    }
    
    public Cliente retornarClienteSessao(){
        Cliente cliente = (Cliente) session.getAttribute("cliente");

        if (cliente == null){
            session.setAttribute("cliente", cliente);
        }

        return cliente;
    }

}
