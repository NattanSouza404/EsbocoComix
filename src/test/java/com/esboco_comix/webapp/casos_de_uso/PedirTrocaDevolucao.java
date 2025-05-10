package com.esboco_comix.webapp.casos_de_uso;

import com.esboco_comix.webapp.paginas.cliente.conta.PaginaConta;
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

    public void pedirTroca() throws InterruptedException {
        paginaMinhasCompras.pedirTroca();
    }

    public void pedirDevolucao() throws InterruptedException {
        paginaMinhasCompras.pedirDevolucao();
    }

    public void logar() throws InterruptedException {
        paginaConta.logar();
    }

    public void abrirMinhasCompras() throws InterruptedException {
        paginaMinhasCompras.abrir();
    }

}
