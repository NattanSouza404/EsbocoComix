package com.esboco_comix.controller.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.esboco_comix.model.Carrinho;

public class SessionManager {

    public static HttpSession getSession(HttpServletRequest req) {
        return req.getSession();
    }

    public static Carrinho retornarCarrinhoSessao(HttpServletRequest req){
        HttpSession session = getSession(req);

        Carrinho carrinho = (Carrinho) session.getAttribute("carrinho");

        if (carrinho == null) {
            carrinho = new Carrinho();
            session.setAttribute("carrinho", new Carrinho());
        }

        return carrinho;   
    }

}
