package com.esboco_comix.webapp;

import org.junit.Test;

import com.esboco_comix.dto.CadastrarClienteDTO;
import com.esboco_comix.webapp.paginas.cadastrar_cliente.PaginaCadastrar;
import com.esboco_comix.webapp.teste_factories.ClienteTesteFactory;
import com.esboco_comix.webapp.teste_factories.EnderecoTesteFactory;

public class PaginaCadastrarTest {

    @Test
    public void cadastrarCliente() {
        PaginaCadastrar cadastro = new PaginaCadastrar();

        CadastrarClienteDTO pedido = new CadastrarClienteDTO();
        pedido.setCliente(ClienteTesteFactory.criar());
        pedido.setEnderecos(EnderecoTesteFactory.criar());
        
        pedido.setSenhaNova("1234abC!");
        pedido.setSenhaConfirmacao("1234abC!");
        
        try {
            cadastro.preencherCliente(pedido);
            cadastro.preencherEnderecos(pedido.getEnderecos());

            cadastro.enviarCadastro();
            cadastro.fechar();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
