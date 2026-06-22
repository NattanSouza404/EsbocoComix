package com.esboco_comix.service.impl.pedido;

import java.util.ArrayList;
import java.util.List;

import com.esboco_comix.model.entidades.CartaoCredito;
import com.esboco_comix.model.entidades.CartaoCreditoPedido;
import com.esboco_comix.model.entidades.Cupom;
import com.esboco_comix.model.entidades.CupomPedido;
import com.esboco_comix.model.entidades.Pedido;
import com.esboco_comix.service.impl.CartaoCreditoService;
import com.esboco_comix.service.impl.CupomService;

public class CalculadoraPedido {

    private CupomService cupomService;
    private CartaoCreditoService cartaoCreditoService;

    public CalculadoraPedido(CupomService cupomService, CartaoCreditoService cartaoCreditoService){
        this.cupomService = cupomService;
        this.cartaoCreditoService = cartaoCreditoService;
    }

    public double calcularValorFormaPagamento(Pedido pedido) {
        List<Cupom> cuponsAplicados = new ArrayList<>();

        int quantCupomPromocional = 0;
        for (CupomPedido cupom : pedido.getCuponsPedido()) {
            Cupom cupomBanco = cupomService.consultarByID(cupom.getIdCupom());

            if (!cupomBanco.isAtivo()) {
                throw new IllegalArgumentException("Cupom inválido para essa compra!");
            }

            if (cupomBanco.isPromocional()) {
                quantCupomPromocional += 1;
            }

            cuponsAplicados.add(cupomBanco);
        }

        if (quantCupomPromocional > 1) {
            throw new IllegalArgumentException("Não é possível usar mais de um cupom promocional na mesma compra!");
        }

        double valorTotal = 0;

        for (CartaoCreditoPedido cartao : pedido.getCartoesCreditoPedido()) {
            CartaoCredito cartaoBanco = cartaoCreditoService.consultarByID(cartao.getIdCartaoCredito());

            if (pedido.getIdCliente() != cartaoBanco.getIdCliente()) {
                throw new IllegalArgumentException("Cartão de crédito não pertence ao cliente da compra!");
            }

            valorTotal += cartao.getValor();
        }

        for (Cupom cupom : cuponsAplicados) {
            valorTotal += cupom.getValor();
        }

        return valorTotal;
    }

}
