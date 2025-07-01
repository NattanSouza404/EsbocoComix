package com.esboco_comix.webapp.testes;

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

            fluxo.realizarPedidoSimples(driver, wait);

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
    public void pedirDevolucao(){
        try {
            PedirTrocaDevolucao fluxo = new PedirTrocaDevolucao(driver, wait);
            MudarStatusPedido fluxoAdmin = new MudarStatusPedido(driver, wait);

            fluxo.realizarPedidoSimples(driver, wait);

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
    public void pedirTrocaDevolucaoItem(){
        try {
            PedirTrocaDevolucao fluxo = new PedirTrocaDevolucao(driver, wait);
            MudarStatusPedido fluxoAdmin = new MudarStatusPedido(driver, wait);

            fluxo.realizarPedidoSimplesDoisItems(driver, wait);

            fluxo.logar();
            fluxo.abrirMinhasCompras();
            fluxo.pedirTrocaItem(0, 1);
            fluxo.pedirDevolucaoItem(1, 1);

            fluxoAdmin.abrirGerenciarVendas();
            fluxoAdmin.mudarStatusItem(0,"Devolução concluída");
            fluxoAdmin.mudarStatusItem(1, "Troca concluída");

            fluxo.logar();
            fluxo.abrirSecaoCupons();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
