package com.esboco_comix.service;

import java.util.List;

import com.esboco_comix.dao.impl.CartaoCreditoDAO;
import com.esboco_comix.model.entidades.CartaoCredito;

public class CartaoCreditoService {
    private CartaoCreditoDAO cartaoCreditoDAO = new CartaoCreditoDAO();

    public void inserir(CartaoCredito c) throws Exception {
        cartaoCreditoDAO.inserir(c);
    }

    public void inserir(List<CartaoCredito> cartoesCliente) throws Exception {
        cartaoCreditoDAO.inserir(null);
        for (CartaoCredito cc : cartoesCliente) {
            inserir(cc);
        }
    }

    public List<CartaoCredito> consultarTodos() throws Exception {
        return cartaoCreditoDAO.consultarTodos();
    }

    public CartaoCredito consultarByID(int id) throws Exception {
        return cartaoCreditoDAO.consultarByID(id);
    }

    public CartaoCredito atualizar(CartaoCredito c) throws Exception {
        return cartaoCreditoDAO.atualizar(c);
    }

    public void deletar(CartaoCredito c) throws Exception {
        cartaoCreditoDAO.deletar(c);
    }

}
