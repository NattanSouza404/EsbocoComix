package com.esboco_comix.webapp.testes;

import com.esboco_comix.webapp.base.BaseTest;
import com.esboco_comix.webapp.paginas.cliente.conta.*;
import org.junit.Test;

import java.time.LocalDate;

public class PaginaContaTest extends BaseTest {
    @Test
    public void editarCadastro() {
        try {
            PaginaConta conta = new PaginaConta(driver, wait);
            conta.logar();
            ModalAlterarDadosPessoais modal = conta.modalAlterarDadosPessoais;
    
            modal.abrirModal();

            modal.preencherInput("nome", "Jorge dos Santos Menezes");
            modal.preencherInput("dataNascimento", LocalDate.of(1998, 12, 20));
            modal.enviar();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    public void atualizarSenha() {
        try {
            PaginaConta conta = new PaginaConta(driver, wait);
            conta.logar();
            ModalAlterarSenha modal = conta.modalAlterarSenha;
            
            modal.abrirModal();
            modal.preencherInput("senhaAntiga", "1234abC!");
            modal.preencherInput("senhaNova", "1234abC!");
            modal.preencherInput("senhaConfirmacao", "1234abC!");

            modal.enviar();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    
    @Test
    public void atualizarEndereco() {
        try {
            PaginaConta conta = new PaginaConta(driver, wait);
            conta.logar();
            ModalAlterarEndereco modal = conta.modalAlterarEndereco;

            modal.abrirModal();
            modal.preencherInput("numero", "25");
            modal.atualizar();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void inserirCartaoCredito() {

        
        try {
            PaginaConta conta = new PaginaConta(driver, wait);
            conta.logar();

            ModalAlterarCartaoCredito modal = conta.modalAlterarCartaoCredito;

            modal.abrirModal();
            modal.adicionarNovoCartao();

            modal.preencherInput("numero", "1111222233334444");
            modal.preencherInput("nomeImpresso", "JORGE DOS SANTOS MENEZES");
            modal.preencherInput("codigoSeguranca", "111");
            modal.preencherInputSelect("bandeiraCartao", "MASTERCARD");
            
            modal.inserir();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void deletarEndereco() {
        
        try {
            PaginaConta conta = new PaginaConta(driver, wait);
            ModalAlterarEndereco modal = conta.modalAlterarEndereco;

            conta.logar();
            modal.abrirModal();

            modal.deletarEndereco();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void deletarCartaoCredito() {
        try {
            PaginaConta conta = new PaginaConta(driver, wait);
            ModalAlterarCartaoCredito modal = conta.modalAlterarCartaoCredito;
    
            modal.abrirModal();

            modal.deletarCartaoCredito();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}