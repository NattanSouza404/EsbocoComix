package com.esboco_comix.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.esboco_comix.dao.impl.cartao_credito.CartaoCreditoDAO;
import com.esboco_comix.dao.impl.cupom.CupomDAO;
import com.esboco_comix.dao.impl.pedido.CartaoCreditoPedidoDAO;
import com.esboco_comix.dao.impl.pedido.CupomPedidoDAO;
import com.esboco_comix.dao.impl.pedido.ItemPedidoDAO;
import com.esboco_comix.dao.impl.pedido.PedidoDAO;
import com.esboco_comix.dao.impl.quadrinho.QuadrinhoDAO;
import com.esboco_comix.dto.ItemCarrinhoDTO;
import com.esboco_comix.model.carrinho.Carrinho;
import com.esboco_comix.model.entidades.CartaoCredito;
import com.esboco_comix.model.entidades.CartaoCreditoPedido;
import com.esboco_comix.model.entidades.Cupom;
import com.esboco_comix.model.entidades.CupomPedido;
import com.esboco_comix.model.entidades.ItemPedido;
import com.esboco_comix.model.entidades.Pedido;
import com.esboco_comix.model.entidades.Quadrinho;
import com.esboco_comix.model.enuns.StatusPedido;

public class PedidoService {

    private PedidoDAO pedidoDAO = new PedidoDAO();
    private ItemPedidoDAO itemPedidoDAO = new ItemPedidoDAO();
    private CartaoCreditoPedidoDAO cartaoCreditoPedidoDAO = new CartaoCreditoPedidoDAO();
    private CupomPedidoDAO cupomPedidoDAO = new CupomPedidoDAO();
    private CupomDAO cupomDAO = new CupomDAO();
    private QuadrinhoDAO quadrinhoDAO = new QuadrinhoDAO();
    private CartaoCreditoDAO cartaoCreditoDAO = new CartaoCreditoDAO();

    public Pedido inserir(Pedido pedido, Carrinho carrinho) throws Exception {

        if (carrinho.getItensCarrinho().isEmpty()) {
            throw new Exception("Nenhum item presente no carrinho!");
        }

        if (pedido.getCartoesCreditoPedido().isEmpty() && pedido.getCuponsPedido().isEmpty()) {
            throw new Exception("Nenhuma forma de pagamento foi provida!");
        }

        Set<Integer> idsCartao = new HashSet<>();

        for (CartaoCreditoPedido cartao : pedido.getCartoesCreditoPedido()) {
            if (!idsCartao.add(cartao.getIdCartaoCredito())){
                throw new Exception("Não é possível usar o mesmo cartão duas vezes no mesmo pedido!");
            }
        }

        double valorTotalPedido = calcularValorTotalPedido(pedido, carrinho);
        double valorTotalPago = calcularValorFormaPagamento(pedido);

        if (valorTotalPago != valorTotalPedido){
            throw new Exception("Valor pago não condiz com valor do pedido!");
        }

        pedido.setStatus(StatusPedido.APROVADO);
        pedido.setItensPedido(carrinho.getItensPedido());

        Pedido pedidoInserido = pedidoDAO.inserir(pedido);

        for (ItemPedido item : pedido.getItensPedido()) {
            item.setIdPedido(pedidoInserido.getId());
            ItemPedido itemInserido = itemPedidoDAO.inserir(item);
            pedidoInserido.getItensPedido().add(itemInserido);
        }

        for (CartaoCreditoPedido cartao : pedido.getCartoesCreditoPedido()) {
            cartao.setIdPedido(pedidoInserido.getId());
            CartaoCreditoPedido cartaoCreditoPedidoInserido = cartaoCreditoPedidoDAO.inserir(cartao);
            pedidoInserido.getCartoesCreditoPedido().add(cartaoCreditoPedidoInserido);
        }

        for (CupomPedido cupom : pedido.getCuponsPedido()) {
            cupom.setIdPedido(pedidoInserido.getId());
            CupomPedido cupomPedidoInserido = cupomPedidoDAO.inserir(cupom);
            pedidoInserido.getCuponsPedido().add(cupomPedidoInserido);
        }

        carrinho.esvaziar();

        return pedidoInserido;
    }

    private double calcularValorFormaPagamento(Pedido pedido) throws Exception {
        List<Cupom> cuponsAplicados = new ArrayList<>();

        int quantCupomPromocional = 0;
        for (CupomPedido cupom : pedido.getCuponsPedido()) {
            Cupom cupomBanco = cupomDAO.consultarByID(cupom.getIdCupom());

            if (!cupomBanco.isAtivo()){
                throw new Exception("Cupom inválido para essa compra!");
            }

            if (cupomBanco.isPromocional()){
                quantCupomPromocional += 1;
            }

            cuponsAplicados.add(cupomBanco);
        }

        if (quantCupomPromocional > 1){
            throw new Exception("Não é possível usar mais de um cupom promocional na mesma compra!");
        }

        double valorTotal = 0;

        for (CartaoCreditoPedido cartao : pedido.getCartoesCreditoPedido()) {
            CartaoCredito cartaoBanco = cartaoCreditoDAO.consultarByID(cartao.getIdCartaoCredito());

            if (pedido.getIdCliente() != cartaoBanco.getIdCliente()){
                throw new Exception("Cartão de crédito não pertence ao cliente da compra!");
            }

            valorTotal += cartao.getValor();
        }

        for (Cupom cupom : cuponsAplicados) {
            valorTotal += cupom.getValor();
        }

        return valorTotal;
    }

    private double calcularValorTotalPedido(Pedido pedido, Carrinho carrinho) throws Exception {
        
        double valorTotal = 0;

        for (ItemCarrinhoDTO item : carrinho.getItensCarrinho()) {
            Quadrinho quadrinho = quadrinhoDAO.consultarByID(item.getIdQuadrinho());
            valorTotal += quadrinho.getPreco() * item.getQuantidade();
        }

        valorTotal += pedido.getValorFrete();

        return valorTotal;
    }

    public List<Pedido> consultarTodos() throws Exception {
        List<Pedido> pedidos = pedidoDAO.consultarTodos();

        for (Pedido p : pedidos) {
            p.setItensPedido(itemPedidoDAO.consultarByIDPedido(p.getId()));
        }

        return pedidos;
    }

    public List<Pedido> consultarPorIDCliente(int idCliente) throws Exception {
        List<Pedido> pedidos = pedidoDAO.consultarByIDCliente(idCliente);

        for (Pedido p : pedidos) {
            p.setItensPedido(itemPedidoDAO.consultarByIDPedido(p.getId()));
        }

        return pedidos;
    }

}
