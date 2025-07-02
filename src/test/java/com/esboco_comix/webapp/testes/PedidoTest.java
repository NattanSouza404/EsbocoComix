package com.esboco_comix.webapp.testes;

import com.esboco_comix.webapp.base.BaseTest;
import com.esboco_comix.webapp.casos_de_uso.FiltrarQuadrinhos;
import com.esboco_comix.webapp.casos_de_uso.FluxoVenda;
import com.esboco_comix.webapp.casos_de_uso.MudarStatusPedido;
import org.junit.Test;

public class PedidoTest extends BaseTest {

    @Test
    public void testePesquisaQuadrinhos(){
        try {
            FiltrarQuadrinhos fluxo = new FiltrarQuadrinhos(driver, wait);

            fluxo.abrirPaginaInicial();

            fluxo.toggleFiltroCategoria("catAção");
            fluxo.pesquisar();

            fluxo.toggleFiltroCategoria("catAção");
            fluxo.adicionarFiltro("titulo", "Homem");
            fluxo.pesquisar();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testeCarrinho(){
        try {
            FluxoVenda fluxo = new FluxoVenda(driver, wait);

            fluxo.abrirPaginaInicial();
            fluxo.adicionarItemAoCarrinho(0);
            fluxo.adicionarItemAoCarrinho(1);
            fluxo.abrirCarrinho();

            fluxo.atualizarItemCarrinho(0, 4);
            fluxo.atualizarItemCarrinho(1, 10);

            fluxo.abrirPaginaInicial();
            fluxo.abrirAnuncio(4);
            fluxo.adicionarItemAoCarrinhoAnuncio(1);
            fluxo.abrirCarrinho();

            // TODO: consultar entradas estoque? consultar pedidos pós venda da página minhasCompras
            // TODO: pagar usando cupom, adicionar cupom promocional ao usuário no teste
            // TODO: adicionar mais de um cartão no pagamento
            // TODO: colocar o insert de um endereço lá em inserts.sql, como também cartões de crédito e mais clientes
            // TODO: testes de inserir, alterar remover etc endereço, cartão de crédito (tomar cuidado para não cagar os outros testes)
            // TODO: melhorar um pouco o estilo das coisas?? (principalmente na página dos clientes)

            fluxo.removerItemCarrinho(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void realizarPedido(){
        try {
            FluxoVenda fluxo = new FluxoVenda(driver, wait);
            MudarStatusPedido fluxoAdmin = new MudarStatusPedido(driver, wait);

            fluxo.adicionarCupomPromocional(50);

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

            fluxo.inserirValorCartao(0, 150.0);
            fluxo.inserirValorCartao(1, 8.0);
            fluxo.realizarPedido();

            fluxo.inserirValorCartao(0, 58.0);
            fluxo.inserirValorCartao(1, 50.0);
            fluxo.adicionarCupomPromocionalAVenda();

            fluxo.realizarPedido();

            fluxoAdmin.abrirGerenciarVendas();
            fluxoAdmin.mudarStatus("Entregue");

            fluxo.abrirPaginaMinhasCompras();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
