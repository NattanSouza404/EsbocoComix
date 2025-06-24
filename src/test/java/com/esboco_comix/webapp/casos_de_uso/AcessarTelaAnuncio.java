package com.esboco_comix.webapp.casos_de_uso;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.esboco_comix.webapp.paginas.cliente.anuncio.PaginaAnuncio;
import com.esboco_comix.webapp.paginas.cliente.carrinho.PaginaCarrinho;

public class AcessarTelaAnuncio {

    private PaginaAnuncio paginaAnuncio;
    private PaginaCarrinho paginaCarrinho;

    public AcessarTelaAnuncio(WebDriver driver, WebDriverWait wait){
        paginaAnuncio = new PaginaAnuncio(driver, wait);
        paginaCarrinho = new PaginaCarrinho(driver, wait);
    }

    public void abrirPaginaAnuncio(int idQuadrinho) throws InterruptedException {
        paginaAnuncio.abrir(idQuadrinho);
    }

    public void adicionarItemAoCarrinho(int quantidade) throws InterruptedException {
        paginaAnuncio.adicionarItemAoCarrinho(quantidade);
    }

    public void abrirPaginaCarrinho() throws InterruptedException {
        paginaCarrinho.abrir();
    }

}
