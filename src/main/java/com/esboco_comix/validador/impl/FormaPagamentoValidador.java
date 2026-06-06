package com.esboco_comix.validador.impl;

import java.util.HashSet;
import java.util.Set;

import com.esboco_comix.model.entidades.CartaoCreditoPedido;
import com.esboco_comix.model.entidades.CupomPedido;
import com.esboco_comix.model.entidades.Pedido;
import com.esboco_comix.service.impl.pedido.CalculadoraPedido;
import com.esboco_comix.validador.AbstractValidador;
import com.esboco_comix.validador.IValidador;

public class FormaPagamentoValidador extends AbstractValidador implements IValidador<Pedido> {

    private final CalculadoraPedido calculadora;

    public FormaPagamentoValidador(CalculadoraPedido calculadoraPedido){
        this.calculadora = calculadoraPedido;
    }

    @Override
    public void validar(Pedido pedido)  {
        if (pedido.getCartoesCreditoPedido().isEmpty() && pedido.getCuponsPedido().isEmpty()) {
            throw new IllegalArgumentException("Nenhuma forma de pagamento foi provida!");
        }

        Set<Integer> idsCartao = new HashSet<>();
        for (CartaoCreditoPedido cartao : pedido.getCartoesCreditoPedido()) {
            if (!idsCartao.add(cartao.getIdCartaoCredito())){
                throw new IllegalArgumentException("Não é possível usar o mesmo cartão duas vezes no mesmo pedido!");
            }

            if (pedido.getCuponsPedido().isEmpty() && cartao.getValor() < 10){
                throw new IllegalArgumentException("Valor do cartão de crédito deve ser no mínimo R$ 10,00");
            }
        }

        Set<Integer> idsCupom = new HashSet<>();
        for (CupomPedido cupom : pedido.getCuponsPedido()) {
            if (!idsCupom.add(cupom.getIdCupom())){
                throw new IllegalArgumentException("Não é possível usar o mesmo cupom duas vezes no mesmo pedido!");
            }
        }

        double valorTotalPedido = calculadora.calcularValorTotalPedido(pedido, pedido.getItensPedido());
        double valorTotalPago = calculadora.calcularValorFormaPagamento(pedido);

        if (valorTotalPago != valorTotalPedido){
            throw new IllegalArgumentException("Valor pago não condiz com valor do pedido!");
        }
    }

}
