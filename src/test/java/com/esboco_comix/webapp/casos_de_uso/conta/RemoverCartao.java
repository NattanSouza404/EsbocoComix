package com.esboco_comix.webapp.casos_de_uso.conta;

import com.esboco_comix.webapp.paginas.cliente.conta.PaginaConta;
import com.esboco_comix.webapp.paginas.cliente.conta.SecoesConta;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RemoverCartao {
    private PaginaConta paginaConta;

    public RemoverCartao(WebDriver driver, WebDriverWait wait){
        paginaConta = new PaginaConta(driver, wait);
    }

    public void logar() throws InterruptedException {
        paginaConta.logar();
    }

    public void trocarSecaoCartaoCredito() throws InterruptedException {
        paginaConta.trocarSecao(SecoesConta.CARTAO_CREDITO);
    }

    public void removerCartao(int index) throws InterruptedException{
        paginaConta.removerCartao(index);
    }

}
