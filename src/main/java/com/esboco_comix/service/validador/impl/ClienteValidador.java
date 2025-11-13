package com.esboco_comix.service.validador.impl;

import com.esboco_comix.dto.CadastrarClienteDTO;
import com.esboco_comix.model.entidades.Cliente;
import com.esboco_comix.service.validador.AbstractValidador;
import com.esboco_comix.service.validador.IValidador;

public class ClienteValidador extends AbstractValidador implements IValidador<Cliente> {
    private final IValidador<CadastrarClienteDTO> senhaValidador = new SenhaValidador();
    private final IValidador<String> emailValidador = new EmailValidador();

    @Override
    public void validar(Cliente cliente) {
        validarAtributoObrigatorio(cliente.getNome(), "Nome");
        validarAtributoObrigatorio(cliente.getCpf(), "CPF");
        validarAtributoObrigatorio(cliente.getGenero(), "Gênero");
        validarAtributoObrigatorio(cliente.getEmail(), "E-mail");
        validarAtributoObrigatorio(cliente.getDataNascimento(), "Data de Nascimento");
        validarAtributoObrigatorio(cliente.getTelefone(), "Telefone");
        validarAtributoObrigatorio(cliente.getTelefone().getDdd(), "DDD");
        validarAtributoObrigatorio(cliente.getTelefone().getNumero(), "Número de telefone");
        validarAtributoObrigatorio(cliente.getTelefone().getTipo(), "Tipo de telefone");

        validarSeApenasNumeros(cliente.getCpf(), "CPF");
        validarSeApenasNumeros(cliente.getTelefone().getDdd(), "DDD");
        validarSeApenasNumeros(cliente.getTelefone().getNumero(), "Número de telefone");
        emailValidador.validar(cliente.getEmail());
    }

    public void validarCadastro(CadastrarClienteDTO pedido) {
        validar(pedido.getCliente());
        senhaValidador.validar(pedido);
    }

}
