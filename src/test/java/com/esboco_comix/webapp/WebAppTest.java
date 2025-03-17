package com.esboco_comix.webapp;

import org.junit.Test;

import com.esboco_comix.controller.pedidos.PedidoAlterarSenha;
import com.esboco_comix.controller.pedidos.PedidoCadastrarCliente;
import com.esboco_comix.model.entidades.Endereco;
import com.esboco_comix.webapp.paginas.cadastrar_cliente.PaginaCadastrar;
import com.esboco_comix.webapp.paginas.conta.PaginaConta;
import com.esboco_comix.webapp.teste_factories.ClienteTesteFactory;
import com.esboco_comix.webapp.teste_factories.EnderecoTesteFactory;

public class WebAppTest {

    @Test
    public void testeCadastro() {
        PaginaCadastrar cadastro = new PaginaCadastrar();

        PedidoCadastrarCliente pedido = new PedidoCadastrarCliente();
        pedido.setCliente(ClienteTesteFactory.criar());
        pedido.setEnderecos(EnderecoTesteFactory.criar());
        
        pedido.setSenhaNova("1234abC!");
        pedido.setSenhaConfirmacao("1234abC!");
        
        try {
            cadastro.preencher(pedido);
            cadastro.enviarCadastro();
            cadastro.fechar();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void editarCadastro() {
        PaginaConta conta = new PaginaConta();
        conta.modalAlterarDadosPessoais.abrirModal();
        conta.modalAlterarDadosPessoais.preencher(null);

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        conta.fechar();
    }

    @Test
    public void atualizarSenha() {
        PaginaConta conta = new PaginaConta();
        conta.modalAlterarSenha.abrirModal();

        PedidoAlterarSenha pedido = new PedidoAlterarSenha();
        pedido.setSenhaAntiga("1234abC!");
        pedido.setSenhaNova("1234abC!");
        pedido.setSenhaConfirmacao("1234abC!");

        conta.modalAlterarSenha.preencher(pedido);
        conta.modalAlterarSenha.enviar();
        conta.fechar();
    }

    @Test
    public void atualizarEndereco() {
        PaginaConta conta = new PaginaConta();
        conta.modalAlterarEndereco.abrirModal();
        conta.modalAlterarEndereco.preencher(new Endereco());
        conta.modalAlterarEndereco.enviar();
        conta.fechar();
    }

    public void atualizarCartaoCredito() {
        PaginaConta conta = new PaginaConta();
        conta.modalAlterarCartaoCredito.abrirModal();
        conta.modalAlterarCartaoCredito.preencher(null);
        conta.modalAlterarCartaoCredito.enviar();
        conta.fechar();
    }
}
