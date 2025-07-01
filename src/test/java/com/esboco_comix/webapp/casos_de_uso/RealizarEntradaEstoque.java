package com.esboco_comix.webapp.casos_de_uso;

import com.esboco_comix.model.entidades.EntradaEstoque;
import com.esboco_comix.webapp.paginas.admin.estoque.PaginaEstoque;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RealizarEntradaEstoque {
    private final PaginaEstoque paginaEstoque;

    public RealizarEntradaEstoque(WebDriver driver, WebDriverWait wait){
        paginaEstoque = new PaginaEstoque(driver, wait);
    }

    public void abrirPaginaEstoque() throws InterruptedException {
        paginaEstoque.abrir();
    }

    public void realizarEntradaEstoque(int index, EntradaEstoque entradaEstoque) throws InterruptedException {
        paginaEstoque.realizarEntradaEstoque(index, entradaEstoque);
    }
}
