package com.esboco_comix.webapp.base.factories;

import com.esboco_comix.dto.CadastrarClienteDTO;

public class CadastrarClienteFactory {

    public static CadastrarClienteDTO criar(){
        CadastrarClienteDTO c = new CadastrarClienteDTO();
        c.setCliente(ClienteTesteFactory.criar());
        c.setEnderecos(EnderecoTesteFactory.criar());
        c.setCartoesCredito(CartaoCreditoTesteFactory.criarListaCartoes());
        c.setSenhaNova("1234abC!");
        c.setSenhaConfirmacao("1234abC!");

        return  c;
    }

}
