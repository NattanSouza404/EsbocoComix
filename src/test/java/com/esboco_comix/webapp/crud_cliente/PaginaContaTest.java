package com.esboco_comix.webapp.crud_cliente;

import java.time.LocalDate;

import org.junit.Test;

import com.esboco_comix.webapp.crud_cliente.conta.ModalAlterarCartaoCredito;
import com.esboco_comix.webapp.crud_cliente.conta.ModalAlterarDadosPessoais;
import com.esboco_comix.webapp.crud_cliente.conta.ModalAlterarEndereco;
import com.esboco_comix.webapp.crud_cliente.conta.ModalAlterarSenha;
import com.esboco_comix.webapp.crud_cliente.conta.PaginaConta;
import com.esboco_comix.webapp.utils.web_driver.DriverFactory;

public class PaginaContaTest {
    @Test
    public void editarCadastro() {
        PaginaConta conta = new PaginaConta(DriverFactory.criaDriver());
        
        try {
            conta.abrir("http://localhost:8080/conta?idcliente=45");
            ModalAlterarDadosPessoais modal = conta.modalAlterarDadosPessoais;
    
            modal.abrirModal();

            modal.preencherInput("nome", "Jorge dos Santos Menezes");
            modal.preencherInput("dataNascimento", LocalDate.of(1998, 12, 20));
            modal.enviar();
            conta.fechar();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void atualizarSenha() {
        PaginaConta conta = new PaginaConta(DriverFactory.criaDriver());

        try {
            conta.abrir("http://localhost:8080/conta?idcliente=45");
            ModalAlterarSenha modal = conta.modalAlterarSenha;
            
            modal.abrirModal();
            modal.preencherInput("senhaAntiga", "1234abC!");
            modal.preencherInput("senhaNova", "1234abC!");
            modal.preencherInput("senhaConfirmacao", "1234abC!");

            modal.enviar();
            conta.fechar();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
    }
    
    @Test
    public void atualizarEndereco() {
        PaginaConta conta = new PaginaConta(DriverFactory.criaDriver());
        
        
        try {
            conta.abrir("http://localhost:8080/conta?idcliente=45");
            ModalAlterarEndereco modal = conta.modalAlterarEndereco;

            modal.abrirModal();
            modal.preencherInput("numero", "25");
            modal.atualizar();
            conta.fechar();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void inserirCartaoCredito() {
        PaginaConta conta = new PaginaConta(DriverFactory.criaDriver());
        
        try {
            conta.abrir("http://localhost:8080/conta?idcliente=45");

            ModalAlterarCartaoCredito modal = conta.modalAlterarCartaoCredito;

            modal.abrirModal();
            modal.adicionarNovoCartao();

            modal.preencherInput("numero", "1111222233334444");
            modal.preencherInput("nomeImpresso", "JORGE DOS SANTOS MENEZES");
            modal.preencherInput("codigoSeguranca", "111");
            modal.preencherInputSelect("bandeiraCartao", "MASTERCARD");
            
            modal.inserir();
            conta.fechar();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void deletarEndereco() {
        
        try {
            PaginaConta conta = new PaginaConta(DriverFactory.criaDriver());
            ModalAlterarEndereco modal = conta.modalAlterarEndereco;

            conta.abrir("http://localhost:8080/conta?idcliente=45");
            modal.abrirModal();

            modal.deletarEndereco();
            conta.fechar();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void deletarCartaoCredito() {
        try {
            PaginaConta conta = new PaginaConta(DriverFactory.criaDriver());
            ModalAlterarCartaoCredito modal = conta.modalAlterarCartaoCredito;
    
            modal.abrirModal();

            modal.deletarCartaoCredito();
            conta.fechar();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}