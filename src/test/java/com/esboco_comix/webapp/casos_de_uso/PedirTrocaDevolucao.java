package com.esboco_comix.webapp.casos_de_uso;

import com.esboco_comix.webapp.paginas.cliente.conta.PaginaConta;
import com.esboco_comix.webapp.paginas.cliente.conta.SecoesConta;
import com.esboco_comix.webapp.paginas.cliente.minhas_compras.PaginaMinhasCompras;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PedirTrocaDevolucao {

    private PaginaConta paginaConta;
    private PaginaMinhasCompras paginaMinhasCompras;

    public PedirTrocaDevolucao(WebDriver driver, WebDriverWait wait) {
        paginaConta = new PaginaConta(driver, wait);
        paginaMinhasCompras = new PaginaMinhasCompras(driver, wait);
    }

    public void pedirTroca(int index) throws InterruptedException {
        paginaMinhasCompras.pedirTroca(index);
    }

    public void pedirDevolucao(int index) throws InterruptedException {
        paginaMinhasCompras.pedirDevolucao(index);
    }

    public void logar() throws InterruptedException {
        paginaConta.logar();
    }

    public void abrirMinhasCompras() throws InterruptedException {
        paginaMinhasCompras.abrir();
    }

    public void pedirTrocaItem(int index) throws InterruptedException {
        paginaMinhasCompras.pedirTrocaItem(index);
    }

    public void pedirDevolucaoItem(int index) throws InterruptedException {
        paginaMinhasCompras.pedirDevolucaoItem(index);
    }

    public void abrirSecaoCupons() throws InterruptedException {
        paginaConta.trocarSecao(SecoesConta.CUPOM);
    }
}
