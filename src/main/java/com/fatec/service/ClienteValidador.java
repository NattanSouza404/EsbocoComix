package com.fatec.service;

import com.fatec.model.entidades.Cliente;

public class ClienteValidador {
    public void validar(Cliente c) throws Exception{
        if (c.getSenha() == null){
            throw new Exception("Senha vazia");
        }
    }
}
