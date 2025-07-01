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

    public void realizarPedidoSimples(WebDriver driver, WebDriverWait wait) throws InterruptedException{
        FluxoVenda fluxo = new FluxoVenda(driver, wait);
        MudarStatusPedido fluxoAdmin = new MudarStatusPedido(driver, wait);

        fluxo.abrirPaginaInicial();
        fluxo.adicionarItemAoCarrinho(0);

        fluxo.logar();

        fluxo.abrirCarrinho();
        fluxo.abrirPaginaCompra();

        fluxo.inserirValorCartao(0, 83.0);
        fluxo.realizarPedido();

        fluxoAdmin.abrirGerenciarVendas();
        fluxoAdmin.mudarStatus("Entregue");
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

    public void pedirTrocaItem(int index, int quantidade) throws InterruptedException {
        paginaMinhasCompras.pedirTrocaItem(index, quantidade);
    }

    public void pedirDevolucaoItem(int index, int quantidade) throws InterruptedException {
        paginaMinhasCompras.pedirDevolucaoItem(index, quantidade);
    }

    public void abrirSecaoCupons() throws InterruptedException {
        paginaConta.mostrarCupons();
    }

    public void realizarPedidoSimplesDoisItems(WebDriver driver, WebDriverWait wait) throws InterruptedException{
        FluxoVenda fluxo = new FluxoVenda(driver, wait);
        MudarStatusPedido fluxoAdmin = new MudarStatusPedido(driver, wait);

        fluxo.abrirPaginaInicial();
        fluxo.adicionarItemAoCarrinho(0);
        fluxo.adicionarItemAoCarrinho(1);

        fluxo.logar();

        fluxo.abrirCarrinho();
        fluxo.abrirPaginaCompra();

        fluxo.inserirValorCartao(0, 158.0);
        fluxo.realizarPedido();

        fluxoAdmin.abrirGerenciarVendas();
        fluxoAdmin.mudarStatus("Entregue");
    }
}
