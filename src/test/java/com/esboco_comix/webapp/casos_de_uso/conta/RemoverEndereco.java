package com.esboco_comix.webapp.casos_de_uso.conta;

import com.esboco_comix.webapp.paginas.cliente.conta.PaginaConta;
import com.esboco_comix.webapp.paginas.cliente.conta.SecoesConta;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RemoverEndereco {
    private PaginaConta paginaConta;

    public RemoverEndereco(WebDriver driver, WebDriverWait wait){
        paginaConta = new PaginaConta(driver, wait);
    }

    public void logar() throws InterruptedException {
        paginaConta.logar();
    }

    public void trocarSecaoEndereco() throws InterruptedException {
        paginaConta.trocarSecao(SecoesConta.ENDERECO);
    }

    public void removerEndereco(int index) throws InterruptedException {
        paginaConta.removerEndereco(index);
    }

}
