package com.esboco_comix.webapp.casos_de_uso.conta;

import com.esboco_comix.webapp.paginas.cliente.conta.ModaisConta;
import com.esboco_comix.webapp.paginas.cliente.conta.PaginaConta;
import com.esboco_comix.webapp.paginas.cliente.conta.SecoesConta;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AdicionarCartao {
    private PaginaConta paginaConta;

    public AdicionarCartao(WebDriver driver, WebDriverWait wait){
        paginaConta = new PaginaConta(driver, wait);
    }

    public void logar() throws InterruptedException {
        paginaConta.logar();
    }

    public void trocarSecaoCartaoCredito() throws InterruptedException {
        paginaConta.trocarSecao(SecoesConta.CARTAO_CREDITO);
    }

    public void abrirModal(){
        paginaConta.abrirModal(ModaisConta.ADICIONAR_CARTAO_CREDITO);
    }

    public void preencherInput(String nome, String valor){
        paginaConta.preencherInputModal(ModaisConta.ADICIONAR_CARTAO_CREDITO, nome, valor);
    }

    public void preencherInputSelect(String nome, String valor){
        paginaConta.preencherInputSelectModal(ModaisConta.ADICIONAR_CARTAO_CREDITO, nome, valor);
    }

    public void enviar() throws InterruptedException {
        paginaConta.enviar(ModaisConta.ADICIONAR_CARTAO_CREDITO);
    }
}