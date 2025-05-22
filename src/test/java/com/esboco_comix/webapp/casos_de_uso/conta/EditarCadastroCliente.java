package com.esboco_comix.webapp.casos_de_uso.conta;

import com.esboco_comix.webapp.paginas.cliente.conta.ModaisConta;
import com.esboco_comix.webapp.paginas.cliente.conta.PaginaConta;
import com.esboco_comix.webapp.paginas.cliente.conta.SecoesConta;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.LocalDate;

public class EditarCadastroCliente {
    private PaginaConta paginaConta;

    public EditarCadastroCliente(WebDriver driver, WebDriverWait wait){
        paginaConta = new PaginaConta(driver, wait);
    }

    public void logar() throws InterruptedException {
        paginaConta.logar();
    }

    public void trocarSecaoDadosPessoais() throws InterruptedException {
        paginaConta.trocarSecao(SecoesConta.DADOS_PESSOAIS);
    }

    public void abrirModalAlterarDadosPessoais(){
        paginaConta.abrirModal(ModaisConta.ALTERAR_DADOS_PESSOAIS);
    }

    public void preencherInputDataModal(String nome, LocalDate data) {
        paginaConta.preencherInputDataModal(ModaisConta.ALTERAR_DADOS_PESSOAIS, nome, data);
    }

    public void preencherInputModal(String nome, String valor) {
        paginaConta.preencherInputModal(ModaisConta.ALTERAR_DADOS_PESSOAIS, nome, valor);
    }

    public void enviarAtualizacao() throws InterruptedException {
        paginaConta.enviar(ModaisConta.ALTERAR_DADOS_PESSOAIS);
    }

}
