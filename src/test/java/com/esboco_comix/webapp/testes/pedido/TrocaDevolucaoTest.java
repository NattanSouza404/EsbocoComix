package com.esboco_comix.webapp.testes.pedido;

import com.esboco_comix.webapp.base.BaseTest;
import com.esboco_comix.webapp.casos_de_uso.MudarStatusPedido;
import com.esboco_comix.webapp.casos_de_uso.PedirTrocaDevolucao;
import org.junit.Test;

public class TrocaDevolucaoTest extends BaseTest {
    @Test
    public void pedirTroca(){
        try {
            PedirTrocaDevolucao fluxo = new PedirTrocaDevolucao(driver, wait);
            MudarStatusPedido fluxoAdmin = new MudarStatusPedido(driver, wait);

            fluxo.logar();
            fluxo.abrirMinhasCompras();
            fluxo.pedirTroca(0);

            fluxoAdmin.abrirGerenciarVendas();
            fluxoAdmin.mudarStatus("Troca Concluída");

            fluxo.logar();
            fluxo.abrirSecaoCupons();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void pedirTrocaItem(){
        try {
            PedirTrocaDevolucao fluxo = new PedirTrocaDevolucao(driver, wait);
            MudarStatusPedido fluxoAdmin = new MudarStatusPedido(driver, wait);

            fluxo.logar();
            fluxo.abrirMinhasCompras();
            fluxo.pedirTrocaItem(0);

            fluxoAdmin.abrirGerenciarVendas();
            fluxoAdmin.mudarStatusItem("Troca concluída");

            fluxo.logar();
            fluxo.abrirSecaoCupons();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void pedirDevolucao(){
        try {
            PedirTrocaDevolucao fluxo = new PedirTrocaDevolucao(driver, wait);
            MudarStatusPedido fluxoAdmin = new MudarStatusPedido(driver, wait);

            fluxo.logar();
            fluxo.abrirMinhasCompras();
            fluxo.pedirDevolucao(0);

            fluxoAdmin.abrirGerenciarVendas();
            fluxoAdmin.mudarStatus("Devolução concluída");

            fluxo.logar();
            fluxo.abrirSecaoCupons();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Test
    public void pedirDevolucaoItem(){
        try {
            PedirTrocaDevolucao fluxo = new PedirTrocaDevolucao(driver, wait);
            MudarStatusPedido fluxoAdmin = new MudarStatusPedido(driver, wait);

            fluxo.logar();
            fluxo.abrirMinhasCompras();
            fluxo.pedirDevolucaoItem(0);

            fluxoAdmin.abrirGerenciarVendas();
            fluxoAdmin.mudarStatusItem("Devolução concluída");

            fluxo.logar();
            fluxo.abrirSecaoCupons();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
