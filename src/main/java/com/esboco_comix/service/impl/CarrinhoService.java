package com.esboco_comix.service.impl;

import jakarta.servlet.http.HttpSession;

import com.esboco_comix.controller.session.Carrinho;
import com.esboco_comix.dto.ItemCarrinhoDTO;

public class CarrinhoService {
    
    public Carrinho retornarCarrinhoSessao(HttpSession session) {
        Carrinho carrinho = (Carrinho) session.getAttribute("carrinho");

        if (carrinho == null) {
            session.setAttribute("carrinho", new Carrinho());
            carrinho = (Carrinho) session.getAttribute("carrinho");
        }

        return carrinho; 
    }

    public Carrinho adicionar(ItemCarrinhoDTO itemCarrinho, HttpSession session) throws Exception {
        Carrinho carrinho = retornarCarrinhoSessao(session);

        if (itemCarrinho.getQuantidade() < 1){
            throw new Exception("Item do carrinho deve ter quantidade maior que 0!");
        }

        carrinho.adicionar(itemCarrinho);

        return carrinho;
    }

    public Carrinho atualizarQuantidade(ItemCarrinhoDTO itemCarrinho, HttpSession session) throws Exception {
        if (itemCarrinho.getQuantidade() < 1){
            throw new Exception("Item do carrinho deve ter quantidade maior que 0!");
        }

        Carrinho carrinho = retornarCarrinhoSessao(session);

        carrinho.atualizarQuantidade(itemCarrinho);

        return carrinho;
    }

    public void deletar(ItemCarrinhoDTO itemCarrinho, HttpSession session) throws Exception {
        Carrinho carrinho = retornarCarrinhoSessao(session);
        carrinho.deletar(itemCarrinho);
    }

}
