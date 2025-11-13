package com.esboco_comix.service.impl;

import java.util.List;

import com.esboco_comix.dao.impl.cartao_credito.CartaoCreditoDAO;
import com.esboco_comix.model.entidades.CartaoCredito;

public class CartaoCreditoService {
    private CartaoCreditoDAO cartaoCreditoDAO = new CartaoCreditoDAO();

    public CartaoCredito inserir(CartaoCredito c) {
        return cartaoCreditoDAO.inserir(c);
    }

    public List<CartaoCredito> consultarByIDCliente(int id) {
        return cartaoCreditoDAO.consultarByIDCliente(id);
    }

    public CartaoCredito consultarByID(int id) {
        return cartaoCreditoDAO.consultarByID(id);
    }

    public CartaoCredito atualizar(CartaoCredito c){
        return cartaoCreditoDAO.atualizar(c);
    }

    public void deletar(CartaoCredito c) {
        cartaoCreditoDAO.inativar(c);
    }

}
