package com.esboco_comix.backend.model.entidades;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.esboco_comix.model.entidades.CartaoCreditoPedido;
import com.esboco_comix.model.entidades.Cupom;
import com.esboco_comix.model.entidades.CupomPedido;
import com.esboco_comix.model.entidades.Pedido;

public class PedidoTest {

    @Test
    public void validarPedidoComFormaDePagamentoValida() {
        Pedido pedido = new Pedido();

        List<CartaoCreditoPedido> cartoesCreditoPedido = new ArrayList<>();
        cartoesCreditoPedido.add(new CartaoCreditoPedido());

        List<CupomPedido> cuponsPedido = new ArrayList<>();
        cuponsPedido.add(new CupomPedido());

        pedido.setCartoesCreditoPedido(cartoesCreditoPedido);
        pedido.setCuponsPedido(cuponsPedido);
        pedido.validarFormaPagamento();
    }

    @Test
    public void validarPedidoSemFormaDePagamento() {
        assertThrows(
            IllegalArgumentException.class,
            () -> {
                Pedido pedido = new Pedido();
        
                pedido.setCartoesCreditoPedido(new ArrayList<>());
                pedido.setCuponsPedido(new ArrayList<>());
                pedido.validarFormaPagamento();
            }
        );
    }

    @Test
    public void validarPedidoComCupomPromocionalJaAplicado() {
        assertThrows(
            IllegalArgumentException.class,
            () -> {
                Pedido pedido = new Pedido();

                Cupom cupomPromocional = new Cupom();
                cupomPromocional.setPromocional(true);
                cupomPromocional.setAtivo(true);

                pedido.aplicarCupom(cupomPromocional);

                Cupom outroCupomPromocional = new Cupom();
                outroCupomPromocional.setPromocional(true);
                outroCupomPromocional.setAtivo(true);

                pedido.aplicarCupom(outroCupomPromocional);
            }
        );
    }
}
