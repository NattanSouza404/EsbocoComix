package com.fatec.service;

import com.fatec.model.entidades.Cliente;

public class ClienteValidador {
    public void validar(Cliente c) throws Exception{
        if (c.getSenha() == null){
            throw new Exception("Senha vazia");
        }

        if (c.getSenha().length() < 8 || c.getSenha().length() > 64){
            throw new Exception("Senha deve ter entre 8 e 64 caracteres!");
        }

    }
}
