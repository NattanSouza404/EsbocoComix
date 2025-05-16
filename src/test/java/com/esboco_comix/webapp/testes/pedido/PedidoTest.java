package com.esboco_comix.webapp.testes.pedido;

import com.esboco_comix.webapp.base.BaseTest;
import com.esboco_comix.webapp.casos_de_uso.FluxoVenda;
import com.esboco_comix.webapp.casos_de_uso.MudarStatusPedido;
import org.junit.Test;

public class PedidoTest extends BaseTest {

    @Test
    public void atualizarItemCarrinho(){
        try {
            FluxoVenda fluxo = new FluxoVenda(driver, wait);
            fluxo.abrirPaginaInicial();
            fluxo.adicionarItemAoCarrinho(0);
            fluxo.adicionarItemAoCarrinho(1);

            fluxo.abrirCarrinho();

            fluxo.atualizarItemCarrinho(0, 4);
            fluxo.atualizarItemCarrinho(1, 10);
            fluxo.abrirCarrinho();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void removerItemCarrinho(){
        try {
            FluxoVenda fluxo = new FluxoVenda(driver, wait);
            fluxo.abrirPaginaInicial();
            fluxo.adicionarItemAoCarrinho(0);
            fluxo.adicionarItemAoCarrinho(1);

            fluxo.abrirCarrinho();

            fluxo.removerItemCarrinho(0);
            fluxo.abrirCarrinho();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void realizarPedido(){
        try {
            FluxoVenda fluxo = new FluxoVenda(driver, wait);
            MudarStatusPedido fluxoAdmin = new MudarStatusPedido(driver, wait);

            fluxo.abrirPaginaInicial();
            fluxo.adicionarItemAoCarrinho(0);
            fluxo.adicionarItemAoCarrinho(1);

            fluxo.logar();

            fluxo.abrirCarrinho();
            fluxo.abrirPaginaCompra();

            fluxo.inserirValorCartao(0, 30.0);
            fluxo.realizarPedido();

            fluxo.adicionarOutroCartao();
            fluxo.selecionarCartao(1, 1);

            fluxo.inserirValorCartao(0, 62.0);
            fluxo.inserirValorCartao(1, 6.0);
            fluxo.realizarPedido();

            fluxo.inserirValorCartao(0, 34.0);
            fluxo.inserirValorCartao(1, 34.0);

            fluxo.realizarPedido();

            fluxoAdmin.abrirGerenciarVendas();
            fluxoAdmin.mudarStatus("Entregue");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void realizarPedidoSimples(){
        try {
            FluxoVenda fluxo = new FluxoVenda(driver, wait);
            MudarStatusPedido fluxoAdmin = new MudarStatusPedido(driver, wait);

            fluxo.abrirPaginaInicial();
            fluxo.adicionarItemAoCarrinho(0);

            fluxo.logar();

            fluxo.abrirCarrinho();
            fluxo.abrirPaginaCompra();

            fluxo.inserirValorCartao(0, 38.0);
            fluxo.realizarPedido();

            fluxoAdmin.abrirGerenciarVendas();
            fluxoAdmin.mudarStatus("Entregue");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
