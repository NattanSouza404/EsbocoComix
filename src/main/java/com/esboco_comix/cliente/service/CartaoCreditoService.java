package com.esboco_comix.cliente.service;

import java.util.List;

import com.esboco_comix.cliente.dao.CartaoCreditoDAO;
import com.esboco_comix.cliente.dominio.entidades.CartaoCredito;

public class CartaoCreditoService {
    private CartaoCreditoDAO cartaoCreditoDAO = new CartaoCreditoDAO();

    public CartaoCredito inserir(CartaoCredito c) {
        c.validar();
        return cartaoCreditoDAO.inserir(c);
    }

    public List<CartaoCredito> consultarByIDCliente(int id) {
        return cartaoCreditoDAO.consultarByIDCliente(id);
    }

    public CartaoCredito consultarByID(int id) {
        return cartaoCreditoDAO.consultarByID(id);
    }

    public CartaoCredito atualizar(CartaoCredito c){
        c.validar();
        return cartaoCreditoDAO.atualizar(c);
    }

    public void deletar(CartaoCredito c) {
        cartaoCreditoDAO.inativar(c);
    }

}
