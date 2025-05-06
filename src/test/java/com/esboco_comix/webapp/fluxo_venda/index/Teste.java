package com.esboco_comix.webapp.fluxo_venda.index;

import org.junit.Test;

import com.esboco_comix.webapp.utils.web_driver.DriverFactory;

public class Teste {
    @Test
    public void realizaPedido(){
        FluxoVenda fluxo = new FluxoVenda(DriverFactory.criaDriver());
        try {
            fluxo.abrir("http://localhost:8080/");
            fluxo.adicionarItemAoCarrinho(0);
            fluxo.adicionarItemAoCarrinho(1);

            fluxo.abrir("http://localhost:8080/conta?idcliente=45");

            fluxo.abrir("http://localhost:8080/carrinho");
            fluxo.irParaCompra();

            fluxo.inserirValorCartao(0, 65.0);
            fluxo.realizarPedido();

            fluxo.fechar();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void mudarStatus(){
        MudarStatusPedido fluxo = new MudarStatusPedido(DriverFactory.criaDriver());

        try {
            fluxo.abrir("http://localhost:8080/admin/gerenciarVendas");
            fluxo.mudarStatus("Entregue");
            fluxo.fechar();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void pedirTroca(){
        try {
            PedirTrocaDevolucao fluxo = new PedirTrocaDevolucao(DriverFactory.criaDriver());
            
            fluxo.abrir("http://localhost:8080/conta?idcliente=45");
            fluxo.abrir("http://localhost:8080/minhasCompras");

            fluxo.pedirTroca();

            fluxo.fechar();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void aceitarTroca(){
        MudarStatusPedido fluxo = new MudarStatusPedido(DriverFactory.criaDriver());

        try {
            fluxo.abrir("http://localhost:8080/admin/gerenciarVendas");
            fluxo.mudarStatus("Troca Concluída");
            fluxo.fechar();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void pedirDevolucao(){
        try {
            PedirTrocaDevolucao fluxo = new PedirTrocaDevolucao(DriverFactory.criaDriver());
            
            fluxo.abrir("http://localhost:8080/conta?idcliente=45");
            fluxo.abrir("http://localhost:8080/minhasCompras");

            fluxo.pedirDevolucao();

            fluxo.fechar();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void aceitarDevolucao(){
        MudarStatusPedido fluxo = new MudarStatusPedido(DriverFactory.criaDriver());

        try {
            fluxo.abrir("http://localhost:8080/admin/gerenciarVendas");
            fluxo.mudarStatus("Devolução concluída");
            fluxo.fechar();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
