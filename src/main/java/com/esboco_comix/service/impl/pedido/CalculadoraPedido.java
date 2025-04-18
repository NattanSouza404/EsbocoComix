package com.esboco_comix.service.impl.pedido;

import java.util.ArrayList;
import java.util.List;

import com.esboco_comix.controller.session.Carrinho;
import com.esboco_comix.dao.impl.cartao_credito.CartaoCreditoDAO;
import com.esboco_comix.dao.impl.cupom.CupomDAO;
import com.esboco_comix.dao.impl.quadrinho.QuadrinhoDAO;
import com.esboco_comix.dto.ItemCarrinhoDTO;
import com.esboco_comix.model.entidades.CartaoCredito;
import com.esboco_comix.model.entidades.CartaoCreditoPedido;
import com.esboco_comix.model.entidades.Cupom;
import com.esboco_comix.model.entidades.CupomPedido;
import com.esboco_comix.model.entidades.Pedido;
import com.esboco_comix.model.entidades.Quadrinho;

public class CalculadoraPedido {

    private CupomDAO cupomDAO = new CupomDAO();
    private QuadrinhoDAO quadrinhoDAO = new QuadrinhoDAO();
    private CartaoCreditoDAO cartaoCreditoDAO = new CartaoCreditoDAO();

    public double calcularValorFormaPagamento(Pedido pedido) throws Exception {
        List<Cupom> cuponsAplicados = new ArrayList<>();

        int quantCupomPromocional = 0;
        for (CupomPedido cupom : pedido.getCuponsPedido()) {
            Cupom cupomBanco = cupomDAO.consultarByID(cupom.getIdCupom());

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
            CartaoCredito cartaoBanco = cartaoCreditoDAO.consultarByID(cartao.getIdCartaoCredito());

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

    public double calcularValorTotalPedido(Pedido pedido, Carrinho carrinho) throws Exception {

        double valorTotal = 0;

        for (ItemCarrinhoDTO item : carrinho.getItensCarrinho()) {
            Quadrinho quadrinho = quadrinhoDAO.consultarByID(item.getIdQuadrinho());
            valorTotal += quadrinho.getPreco() * item.getQuantidade();
        }

        valorTotal += pedido.getValorFrete();

        return valorTotal;
    }
}
