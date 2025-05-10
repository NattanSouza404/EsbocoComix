package com.esboco_comix.webapp.casos_de_uso;

import com.esboco_comix.webapp.paginas.cliente.carrinho.PaginaCarrinho;
import com.esboco_comix.webapp.paginas.cliente.compra.PaginaCompra;
import com.esboco_comix.webapp.paginas.cliente.conta.PaginaConta;
import com.esboco_comix.webapp.paginas.cliente.index.PaginaIndex;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FluxoVenda {

    private PaginaIndex paginaIndex;
    private PaginaCompra paginaCompra;
    private PaginaCarrinho paginaCarrinho;
    private PaginaConta paginaConta;
    
    public FluxoVenda(WebDriver driver, WebDriverWait wait) {
        paginaIndex = new PaginaIndex(driver, wait);
        paginaCompra = new PaginaCompra(driver, wait);
        paginaCarrinho = new PaginaCarrinho(driver, wait);
        paginaConta = new PaginaConta(driver, wait);
    }

    public void abrirPaginaInicial() throws InterruptedException {
        paginaIndex.abrir();
    }

    public void adicionarItemAoCarrinho(int index) throws InterruptedException {
        paginaIndex.adicionarItemAoCarrinho(index);
    }

    public void abrirCarrinho() throws InterruptedException {
        paginaCarrinho.abrir();
    }

    public void logar() throws InterruptedException {
        paginaConta.logar();
    }

    public void abrirPaginaCompra() throws InterruptedException{
        paginaCarrinho.irParaCompra();
    }

    public void adicionarOutroCartao() throws InterruptedException {
        paginaCompra.adicionarOutroCartao();
    }

    public void inserirValorCartao(int index, double valor) throws InterruptedException {
        paginaCompra.inserirValorCartao(index, valor);
    }

    public void realizarPedido() throws InterruptedException {
        paginaCompra.realizarPedido();
    }

    public void atualizarItemCarrinho(int index, int quantidade) throws InterruptedException {
        paginaCarrinho.atualizarItemCarrinho(index, quantidade);
    }

    public void removerItemCarrinho(int index) throws InterruptedException {
        paginaCarrinho.removerItemCarrinho(index);
    }
}
