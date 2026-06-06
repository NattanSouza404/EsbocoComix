package com.esboco_comix.webapp.base.factories;

import com.esboco_comix.dto.CadastrarClienteDTO;

public class CadastrarClienteFactory {

    public static CadastrarClienteDTO criar(){
        return CadastrarClienteDTO.builder()
            .cliente(ClienteTesteFactory.criar())
            .enderecos(EnderecoTesteFactory.criar())
            .cartoesCredito(CartaoCreditoTesteFactory.criarListaCartoes())
            .senhaNova("1234abC!")
            .senhaConfirmacao("1234abC!")
        .build();
    }

}
