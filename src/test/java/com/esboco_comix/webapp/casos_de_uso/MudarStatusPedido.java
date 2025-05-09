package com.esboco_comix.webapp.casos_de_uso;

import com.esboco_comix.webapp.paginas.admin.gerenciar_vendas.PaginaGerenciarVendas;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MudarStatusPedido {

    private PaginaGerenciarVendas paginaGerenciarVendas;

    public MudarStatusPedido(WebDriver driver, WebDriverWait wait) {
        paginaGerenciarVendas = new PaginaGerenciarVendas(driver, wait);
    }

    public void mudarStatus(String status) throws InterruptedException {
        paginaGerenciarVendas.mudarStatus(status);
    }

    public void abrirGerenciarVendas() {
        paginaGerenciarVendas.abrir();
    }
}
