package com.esboco_comix.webapp.testes;

import com.esboco_comix.webapp.base.BaseTest;
import com.esboco_comix.webapp.casos_de_uso.FluxoVenda;
import com.esboco_comix.webapp.casos_de_uso.MudarStatusPedido;
import com.esboco_comix.webapp.casos_de_uso.PedirTrocaDevolucao;
import org.junit.Test;

public class PedidoTest extends BaseTest {

    @Test
    public void realizaPedido(){

        try {
            FluxoVenda fluxo = new FluxoVenda(driver, wait);
            fluxo.abrirPaginaInicial();
            fluxo.adicionarItemAoCarrinho(0);
            fluxo.adicionarItemAoCarrinho(1);

            fluxo.logar();

            fluxo.abrirCarrinho();
            fluxo.abrirPaginaCompra();

            fluxo.inserirValorCartao(0, 65.0);
            fluxo.realizarPedido();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void mudarStatus(){

        try {
            MudarStatusPedido fluxo = new MudarStatusPedido(driver, wait);
            fluxo.abrirGerenciarVendas();
            fluxo.mudarStatus("Entregue");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void pedirTroca(){
        try {
            PedirTrocaDevolucao fluxo = new PedirTrocaDevolucao(driver, wait);

            fluxo.logar();
            fluxo.abrirMinhasCompras();

            fluxo.pedirTroca();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void aceitarTroca(){
        try {
            MudarStatusPedido fluxo = new MudarStatusPedido(driver, wait);
            fluxo.abrirGerenciarVendas();
            fluxo.mudarStatus("Troca Concluída");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void pedirDevolucao(){
        try {
            PedirTrocaDevolucao fluxo = new PedirTrocaDevolucao(driver, wait);

            fluxo.logar();
            fluxo.abrirMinhasCompras();
            fluxo.pedirDevolucao();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void aceitarDevolucao(){
        try {
            MudarStatusPedido fluxo = new MudarStatusPedido(driver, wait);
            fluxo.abrirGerenciarVendas();
            fluxo.mudarStatus("Devolução concluída");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
