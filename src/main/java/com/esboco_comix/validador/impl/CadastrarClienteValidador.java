package com.esboco_comix.validador.impl;

import com.esboco_comix.dto.CadastrarClienteDTO;
import com.esboco_comix.model.entidades.CartaoCredito;
import com.esboco_comix.model.entidades.Endereco;
import com.esboco_comix.validador.AbstractValidador;
import com.esboco_comix.validador.IValidador;
import com.esboco_comix.validador.impl.cartao_credito.CartaoCreditoValidador;
import com.esboco_comix.validador.impl.cliente.ClienteValidador;
import com.esboco_comix.validador.impl.endereco.EnderecoValidador;

public class CadastrarClienteValidador extends AbstractValidador
    implements IValidador<CadastrarClienteDTO>{

    private final ClienteValidador clienteValidador = new ClienteValidador();
    private final EnderecoValidador enderecoValidador = new EnderecoValidador();
    private final CartaoCreditoValidador cartaoCreditoValidador = new CartaoCreditoValidador();

    @Override
    public void validar(CadastrarClienteDTO cadastro) {
        clienteValidador.validar(cadastro.getCliente());

        for (Endereco e : cadastro.getEnderecos()) {
            enderecoValidador.validar(e);
        }

        for (CartaoCredito c : cadastro.getCartoesCredito()) {
            cartaoCreditoValidador.validar(c);
        }
    }
    
}
