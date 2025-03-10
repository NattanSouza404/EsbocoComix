package com.esboco_comix.service;

import com.esboco_comix.controller.pedidos.PedidoCadastrarCliente;
import com.esboco_comix.model.entidades.Cliente;

public class ClienteValidador {
    public void validar(Cliente c, PedidoCadastrarCliente pedido) throws Exception{
        if (pedido.getSenhaNova() == null){
            throw new Exception("Senha vazia");
        }

        if (pedido.getSenhaNova().length() < 8 || pedido.getSenhaNova().length() > 64){
            throw new Exception("Senha deve ter entre 8 e 64 caracteres!");
        }
    }
}
