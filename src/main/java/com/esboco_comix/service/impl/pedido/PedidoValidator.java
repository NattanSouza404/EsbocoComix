package com.esboco_comix.service.impl.pedido;

import java.util.HashSet;
import java.util.Set;

import com.esboco_comix.controller.session.Carrinho;
import com.esboco_comix.model.entidades.CartaoCreditoPedido;
import com.esboco_comix.model.entidades.CupomPedido;
import com.esboco_comix.model.entidades.Pedido;

public class PedidoValidator {

    private CalculadoraPedido calculadora;

    public PedidoValidator(CalculadoraPedido calculadoraPedido){
        this.calculadora = calculadoraPedido;
    }
    
    public void validarFormaPagamento(Pedido pedido, Carrinho carrinho) throws Exception {
        if (pedido.getCartoesCreditoPedido().isEmpty() && pedido.getCuponsPedido().isEmpty()) {
            throw new Exception("Nenhuma forma de pagamento foi provida!");
        }

        Set<Integer> idsCartao = new HashSet<>();
        for (CartaoCreditoPedido cartao : pedido.getCartoesCreditoPedido()) {
            if (!idsCartao.add(cartao.getIdCartaoCredito())){
                throw new Exception("Não é possível usar o mesmo cartão duas vezes no mesmo pedido!");
            }
        }

        for (CartaoCreditoPedido cc : pedido.getCartoesCreditoPedido()) {
            if (cc.getValor() < 10){
                throw new Exception("Valor do cartão de crédito deve ser no mínimo R$ 10,00");
            }
        }

        Set<Integer> idsCupom = new HashSet<>();
        for (CupomPedido cupom : pedido.getCuponsPedido()) {
            if (!idsCupom.add(cupom.getIdCupom())){
                throw new Exception("Não é possível usar o mesmo cupom duas vezes no mesmo pedido!");
            }
        }

        double valorTotalPedido = calculadora.calcularValorTotalPedido(pedido, carrinho.getItensPedido());
        double valorTotalPago = calculadora.calcularValorFormaPagamento(pedido);

        if (valorTotalPago != valorTotalPedido){
            throw new Exception("Valor pago não condiz com valor do pedido!");
        }
    }

}
