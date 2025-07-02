package com.esboco_comix.webapp.casos_de_uso;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.esboco_comix.webapp.paginas.cliente.index.PaginaIndex;

public class FiltrarQuadrinhos {

    private PaginaIndex paginaIndex;

    public FiltrarQuadrinhos(WebDriver driver, WebDriverWait wait){
        paginaIndex = new PaginaIndex(driver, wait);
    }

    public void abrirPaginaInicial() throws InterruptedException{
        paginaIndex.abrir();
    }

    public void toggleFiltroCategoria(String categoria) throws InterruptedException{
        paginaIndex.toggleFiltroCategoria(categoria);
    }

    public void pesquisar() throws InterruptedException {
        paginaIndex.pesquisar();
    }

    public void adicionarFiltro(String nome, String valor) throws InterruptedException {
        paginaIndex.adicionarFiltro(nome, valor);
    }

}
