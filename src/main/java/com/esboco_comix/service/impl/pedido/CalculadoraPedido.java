package com.esboco_comix.service.impl.pedido;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.esboco_comix.model.entidades.CartaoCredito;
import com.esboco_comix.model.entidades.CartaoCreditoPedido;
import com.esboco_comix.model.entidades.Cupom;
import com.esboco_comix.model.entidades.CupomPedido;
import com.esboco_comix.model.entidades.ItemPedido;
import com.esboco_comix.model.entidades.Pedido;
import com.esboco_comix.model.entidades.Quadrinho;
import com.esboco_comix.service.impl.CartaoCreditoService;
import com.esboco_comix.service.impl.CupomService;
import com.esboco_comix.service.impl.QuadrinhoService;

public class CalculadoraPedido {

    private CupomService cupomService;
    private QuadrinhoService quadrinhoService;
    private CartaoCreditoService cartaoCreditoService;

    public CalculadoraPedido(CupomService cupomService, QuadrinhoService quadrinhoService, CartaoCreditoService cartaoCreditoService){
        this.cupomService = cupomService;
        this.quadrinhoService = quadrinhoService;
        this.cartaoCreditoService = cartaoCreditoService;
    }

    public double calcularValorFormaPagamento(Pedido pedido) throws Exception {
        List<Cupom> cuponsAplicados = new ArrayList<>();

        int quantCupomPromocional = 0;
        for (CupomPedido cupom : pedido.getCuponsPedido()) {
            Cupom cupomBanco = cupomService.consultarByID(cupom.getIdCupom());

            if (!cupomBanco.isAtivo()) {
                throw new Exception("Cupom inválido para essa compra!");
            }

            if (cupomBanco.isPromocional()) {
                quantCupomPromocional += 1;
            }

            cuponsAplicados.add(cupomBanco);
        }

        if (quantCupomPromocional > 1) {
            throw new Exception("Não é possível usar mais de um cupom promocional na mesma compra!");
        }

        double valorTotal = 0;

        for (CartaoCreditoPedido cartao : pedido.getCartoesCreditoPedido()) {
            CartaoCredito cartaoBanco = cartaoCreditoService.consultarByID(cartao.getIdCartaoCredito());

            if (pedido.getIdCliente() != cartaoBanco.getIdCliente()) {
                throw new Exception("Cartão de crédito não pertence ao cliente da compra!");
            }

            valorTotal += cartao.getValor();
        }

        for (Cupom cupom : cuponsAplicados) {
            valorTotal += cupom.getValor();
        }

        return valorTotal;
    }

    public double calcularValorTotalPedido(Pedido pedido, List<ItemPedido> itensPedido) throws Exception {
        Map<Integer, Quadrinho> quadrinhoMap = quadrinhoService.consultarTodos().stream()
            .collect(Collectors.toMap(Quadrinho::getId, quadrinho -> quadrinho));

        double valor = 0;
        for (ItemPedido itemPedido : itensPedido) {
            Quadrinho quadrinho = quadrinhoMap.get(itemPedido.getIdQuadrinho());

            if (quadrinho == null) {
                throw new Exception("Quadrinho do pedido não encontrado!");
            }

            valor += quadrinho.getPreco() * itemPedido.getQuantidade();
        }

        valor += pedido.getValorFrete();

        return valor;

    }

    public double calcularItemPedido(ItemPedido itemPedido) throws Exception {
        Quadrinho quadrinho = quadrinhoService.consultarByID(itemPedido.getIdQuadrinho());
        return quadrinho.getPreco() * itemPedido.getQuantidade();
    }
}
