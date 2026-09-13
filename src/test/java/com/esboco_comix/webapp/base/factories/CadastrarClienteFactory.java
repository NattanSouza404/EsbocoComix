package com.esboco_comix.webapp.base.factories;

import com.esboco_comix.cliente.dominio.entidades.Cliente;
import com.esboco_comix.cliente.dto.CadastrarClienteDTO;

public class CadastrarClienteFactory {

    public static CadastrarClienteDTO criar(){
        Cliente cliente = ClienteTesteFactory.criar();

        return CadastrarClienteDTO.builder()
            .nome(cliente.getNome())
            .genero(cliente.getGenero())
            .dataNascimento(cliente.getDataNascimento())
            .cpf(cliente.getCpf().valor())
            .email(cliente.getEmail().valor())
            .enderecos(EnderecoTesteFactory.criar())
            .cartoesCredito(CartaoCreditoTesteFactory.criarListaCartoes())
            .senhaNova("1234abC!")
            .senhaConfirmacao("1234abC!")
        .build();
    }

}
