package com.esboco_comix.sessao;

import com.esboco_comix.model.Carrinho;

import jakarta.servlet.http.HttpSession;

public class SessaoService {
    private static final String CARRINHO = "carrinho";

    public Carrinho retornarCarrinho(HttpSession session) {
        Carrinho carrinho = (Carrinho) session.getAttribute(CARRINHO);

        if (carrinho == null) {
            carrinho = new Carrinho();
            session.setAttribute(CARRINHO, carrinho);
        }

        return carrinho; 
    }
}
