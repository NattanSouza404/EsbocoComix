package com.esboco_comix.webapp.fluxo_venda.index;

import org.junit.Test;

import com.esboco_comix.webapp.utils.web_driver.DriverTeste;

public class Teste {
    @Test
    public void adicionarItemAoCarrinho(){
        FluxoVenda index = new FluxoVenda(new DriverTeste());
        try {
            index.adicionarItemAoCarrinho(0);
            index.adicionarItemAoCarrinho(1);

            index.abrirConta();
            index.abrirCarrinho();
            index.irParaCompra();
            index.inserirValorCartao(0, 65.0);
            index.realizarPedido();

            index.fechar();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void mudarStatus(){
        MudarStatus pagina = new MudarStatus(new DriverTeste());

        try {
            pagina.abrir();
            pagina.mudarStatus("Entregue");
            pagina.fechar();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void aceitarTroca(){
        MudarStatus a = new MudarStatus(new DriverTeste());

        try {
            a.abrir();
            a.mudarStatus("Troca concluída");
            a.fechar();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void pedirTroca(){
        try {
            PedirTroca pedirTroca = new PedirTroca(new DriverTeste());

            pedirTroca.abrir();

            pedirTroca.pedirTroca();

            pedirTroca.fechar();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
