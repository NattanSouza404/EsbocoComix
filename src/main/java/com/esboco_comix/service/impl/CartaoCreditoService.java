package com.esboco_comix.service.impl;

import java.util.List;

import com.esboco_comix.dao.impl.cartao_credito.CartaoCreditoDAO;
import com.esboco_comix.model.entidades.CartaoCredito;
import com.esboco_comix.validador.impl.cartao_credito.CartaoCreditoValidador;

public class CartaoCreditoService {
    private CartaoCreditoDAO cartaoCreditoDAO = new CartaoCreditoDAO();
    private CartaoCreditoValidador cartaoCreditoValidador = new CartaoCreditoValidador();

    public CartaoCredito inserir(CartaoCredito c) {
        // TODO: ao inserir um cliente, acabamos verificando o cartão de crédito 2 vezes
        cartaoCreditoValidador.validar(c);
        return cartaoCreditoDAO.inserir(c);
    }

    public List<CartaoCredito> consultarByIDCliente(int id) {
        return cartaoCreditoDAO.consultarByIDCliente(id);
    }

    public CartaoCredito consultarByID(int id) {
        return cartaoCreditoDAO.consultarByID(id);
    }

    public CartaoCredito atualizar(CartaoCredito c){
        cartaoCreditoValidador.validar(c);
        return cartaoCreditoDAO.atualizar(c);
    }

    public void deletar(CartaoCredito c) {
        cartaoCreditoDAO.inativar(c);
    }

}
