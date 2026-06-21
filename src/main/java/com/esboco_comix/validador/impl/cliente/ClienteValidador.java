package com.esboco_comix.validador.impl.cliente;

import com.esboco_comix.dto.CadastrarClienteDTO;
import com.esboco_comix.model.entidades.Cliente;
import com.esboco_comix.validador.AbstractValidador;
import com.esboco_comix.validador.IValidador;

public class ClienteValidador extends AbstractValidador implements IValidador<Cliente> {
    
    private final IValidador<CadastrarClienteDTO> senhaValidador = new SenhaValidador();

    @Override
    public void validar(Cliente cliente) {
        validarAtributoObrigatorio(cliente.getNome(), "Nome");

        if (cliente.getNome().length() > 100){
            throw new IllegalArgumentException("Nome deve conter menos de 100 caracteres!");
        }

        validarAtributoObrigatorio(cliente.getGenero(), "Gênero");
        validarAtributoObrigatorio(cliente.getDataNascimento(), "Data de Nascimento");
    }

    public void validarCadastro(CadastrarClienteDTO pedido) {
        validar(pedido.getCliente());
        senhaValidador.validar(pedido);
    }

}
