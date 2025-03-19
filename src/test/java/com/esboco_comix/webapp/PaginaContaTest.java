package com.esboco_comix.webapp;

import java.time.LocalDate;

import org.junit.Test;

import com.esboco_comix.webapp.paginas.conta.ModalAlterarCartaoCredito;
import com.esboco_comix.webapp.paginas.conta.ModalAlterarDadosPessoais;
import com.esboco_comix.webapp.paginas.conta.ModalAlterarEndereco;
import com.esboco_comix.webapp.paginas.conta.ModalAlterarSenha;
import com.esboco_comix.webapp.paginas.conta.PaginaConta;

public class PaginaContaTest {
     @Test
    public void editarCadastro() {
        PaginaConta conta = new PaginaConta();
        ModalAlterarDadosPessoais modal = conta.modalAlterarDadosPessoais;

        modal.abrirModal();
        
        try {
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
        PaginaConta conta = new PaginaConta();
        ModalAlterarSenha modal = conta.modalAlterarSenha;
        
        modal.abrirModal();
        
        try {
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
        PaginaConta conta = new PaginaConta();
        ModalAlterarEndereco modal = conta.modalAlterarEndereco;

        modal.abrirModal();
        
        try {
            modal.preencherInput("numero", "25");
            modal.atualizar();
            conta.fechar();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void inserirCartaoCredito() {
        PaginaConta conta = new PaginaConta();
        ModalAlterarCartaoCredito modal = conta.modalAlterarCartaoCredito;

        modal.abrirModal();
        modal.adicionarNovoCartao();
        
        try {
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
        PaginaConta conta = new PaginaConta();
        ModalAlterarEndereco modal = conta.modalAlterarEndereco;

        modal.abrirModal();
        try {
            modal.deletarEndereco();
            conta.fechar();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void deletarCartaoCredito() {
        PaginaConta conta = new PaginaConta();
        ModalAlterarCartaoCredito modal = conta.modalAlterarCartaoCredito;

        modal.abrirModal();
        try {
            modal.deletarCartaoCredito();
            conta.fechar();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}