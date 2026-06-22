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

    public double calcularValorTotal() {
        double valor = 0;
        for (ItemPedido itemPedido : itensPedido) {
            valor += itemPedido.calcularValor();
        }

        return valor + valorFrete;
    }

    public void atualizarValorTotal() {
        this.valorTotal = calcularValorTotal();
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
}
