package com.esboco_comix.validador.impl;

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
        pedido.validarFormaPagamento();

        double valorTotalPedido = calculadora.calcularValorTotalPedido(pedido, pedido.getItensPedido());
        double valorTotalPago = calculadora.calcularValorFormaPagamento(pedido);

        if (valorTotalPago != valorTotalPedido){
            throw new IllegalArgumentException("Valor pago não condiz com valor do pedido!");
        }
    }

}
