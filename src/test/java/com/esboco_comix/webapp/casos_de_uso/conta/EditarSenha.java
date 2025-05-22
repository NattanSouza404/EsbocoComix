package com.esboco_comix.webapp.casos_de_uso.conta;

import com.esboco_comix.webapp.paginas.cliente.conta.ModaisConta;
import com.esboco_comix.webapp.paginas.cliente.conta.PaginaConta;
import com.esboco_comix.webapp.paginas.cliente.conta.SecoesConta;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.LocalDate;

public class EditarSenha {
    private PaginaConta paginaConta;

    public EditarSenha(WebDriver driver, WebDriverWait wait){
        paginaConta = new PaginaConta(driver, wait);
    }

    public void logar() throws InterruptedException {
        paginaConta.logar();
    }

    public void trocarSecaoDadosPessoais() throws InterruptedException {
        paginaConta.trocarSecao(SecoesConta.DADOS_PESSOAIS);
    }

    public void abrirModalAlterarSenha(){
        paginaConta.abrirModal(ModaisConta.ALTERAR_SENHA);
    }

    public void preencherInputModal(String nome, String valor) {
        paginaConta.preencherInputModal(ModaisConta.ALTERAR_SENHA, nome, valor);
    }

    public void enviarAtualizacao() throws InterruptedException {
        paginaConta.enviar(ModaisConta.ALTERAR_SENHA);
    }
}
