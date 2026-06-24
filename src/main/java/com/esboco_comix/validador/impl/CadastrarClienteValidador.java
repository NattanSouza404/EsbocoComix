package com.esboco_comix.validador.impl;

import com.esboco_comix.dto.CadastrarClienteDTO;
import com.esboco_comix.model.entidades.CartaoCredito;
import com.esboco_comix.model.entidades.Endereco;
import com.esboco_comix.validador.AbstractValidador;
import com.esboco_comix.validador.IValidador;
import com.esboco_comix.validador.impl.cliente.SenhaValidador;

public class CadastrarClienteValidador extends AbstractValidador
    implements IValidador<CadastrarClienteDTO>{

    private final SenhaValidador senhaValidador = new SenhaValidador();

    @Override
    public void validar(CadastrarClienteDTO cadastro) {
        cadastro.getCliente().validar();
        senhaValidador.validar(cadastro);

        for (Endereco e : cadastro.getEnderecos()) {
            e.validar();
        }

        for (CartaoCredito c : cadastro.getCartoesCredito()) {
            c.validar();
        }
    }
    
}
