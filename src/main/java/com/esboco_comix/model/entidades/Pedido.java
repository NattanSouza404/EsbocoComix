package com.esboco_comix.model.entidades;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.esboco_comix.model.enuns.StatusPedido;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Pedido {
    private int id;
    private int idCliente;
    private StatusPedido status;
    private Double valorTotal;
    private Double valorFrete;
    private LocalDateTime data;

    private List<ItemPedido> itensPedido = new ArrayList<>();
    private Endereco enderecoEntrega;
    
    private List<CartaoCreditoPedido> cartoesCreditoPedido = new ArrayList<>();
    private List<CupomPedido> cuponsPedido = new ArrayList<>();

    private List<Cupom> cuponsAplicados = new ArrayList<>();

    public double calcularValorTotal() {
        double valor = 0;
        for (ItemPedido itemPedido : itensPedido) {
            valor += itemPedido.calcularValor();
        }

        return valor + valorFrete;
    }

    public double getValorTotalPago() {
        double valorTotal = 0;

        for (CartaoCreditoPedido cartao : cartoesCreditoPedido) {
            valorTotal += cartao.getValor();
        }

        for (Cupom cupom : cuponsAplicados) {
            valorTotal += cupom.getValor();
        }

        return valorTotal;
    }

    public void atualizarValorTotal() {
        this.valorTotal = calcularValorTotal();
    }

    public void aplicarCupom(Cupom cupom) {
        if (!cupom.isAtivo()) {
            throw new IllegalArgumentException("Cupom inválido para essa compra!");
        }

        if (cupom.isPromocional() && jaPossuiCupomPromocional()){         
            throw new IllegalArgumentException("Já existe um cupom promocional aplicado para esse pedido!");
        }

        cuponsAplicados.add(cupom);
    }

    public void aplicarCartaoCredito(CartaoCreditoPedido cartao) {
        for (CartaoCreditoPedido c : cartoesCreditoPedido) {
            if (c.getIdCartaoCredito() == cartao.getIdCartaoCredito()) {
                throw new IllegalArgumentException("Não é possível usar o mesmo cartão duas vezes no mesmo pedido!");
            }
        }

        if (cuponsAplicados.isEmpty() && cartao.getValor() < 10){
            throw new IllegalArgumentException("Valor do cartão de crédito deve ser no mínimo R$ 10,00");
        }

        cartoesCreditoPedido.add(cartao);
    }

    public void validarFormaPagamento() {
        if (cartoesCreditoPedido.isEmpty() && cuponsPedido.isEmpty()) {
            throw new IllegalArgumentException("Nenhuma forma de pagamento foi provida!");
        }

        Set<Integer> idsCartao = new HashSet<>();
        for (CartaoCreditoPedido cartao : cartoesCreditoPedido) {
            if (!idsCartao.add(cartao.getIdCartaoCredito())){
                throw new IllegalArgumentException("Não é possível usar o mesmo cartão duas vezes no mesmo pedido!");
            }

            if (cuponsPedido.isEmpty() && cartao.getValor() < 10){
                throw new IllegalArgumentException("Valor do cartão de crédito deve ser no mínimo R$ 10,00");
            }
        }

        Set<Integer> idsCupom = new HashSet<>();
        for (CupomPedido cupom : cuponsPedido) {
            if (!idsCupom.add(cupom.getIdCupom())){
                throw new IllegalArgumentException("Não é possível usar o mesmo cupom duas vezes no mesmo pedido!");
            }
        }
    }

    private boolean jaPossuiCupomPromocional() {
        for (Cupom c : cuponsAplicados) {
            if (c.isPromocional()) {
                return true;
            }
        }
        return false;
    }
}
