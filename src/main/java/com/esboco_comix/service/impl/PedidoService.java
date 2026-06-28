package com.esboco_comix.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.esboco_comix.dao.impl.pedido.*;
import com.esboco_comix.dto.AtualizarPedidoDTO;

import com.esboco_comix.dto.ItemPedidoDTO;
import com.esboco_comix.dto.PedidoDTO;
import com.esboco_comix.dto.PedidoPosVendaDTO;
import com.esboco_comix.dto.QuadrinhoDTO;
import com.esboco_comix.model.Carrinho;
import com.esboco_comix.model.entidades.*;
import com.esboco_comix.model.enuns.StatusPedido;

public class PedidoService {

    private final CupomService cupomService = new CupomService();
    private final QuadrinhoService quadrinhoService = new QuadrinhoService();
    private final EstoqueService estoqueService = new EstoqueService();
    private final CartaoCreditoService cartaoCreditoService = new CartaoCreditoService();

    private final PedidoDAO pedidoDAO = new PedidoDAO();
    private final ItemPedidoDAO itemPedidoDAO = new ItemPedidoDAO();
    private final CartaoCreditoPedidoDAO cartaoCreditoPedidoDAO = new CartaoCreditoPedidoDAO();
    private final CupomPedidoDAO cupomPedidoDAO = new CupomPedidoDAO();

    private final PedidoPosVendaDAO pedidoPosVendaDAO = new PedidoPosVendaDAO();

    public Pedido inserir(Pedido pedido, Carrinho carrinho) {
        if (carrinho.isVazio()) {
            throw new IllegalStateException("Nenhum item presente no carrinho!");
        }

        pedido.setItensPedido(carrinho.getItensPedido());

        pedido.validarFormaPagamento();

        for (CartaoCreditoPedido cartao : pedido.getCartoesCreditoPedido()) {
            CartaoCredito cartaoBanco = cartaoCreditoService.consultarByID(cartao.getIdCartaoCredito());

            if (pedido.getIdCliente() != cartaoBanco.getIdCliente()) {
                throw new IllegalArgumentException("Cartão de crédito não pertence ao cliente da compra!");
            }
        }

        double valorTotalPedido = pedido.calcularValorTotal();
        double valorTotalPago = pedido.getValorTotalPago();

        if (valorTotalPago != valorTotalPedido){
            throw new IllegalArgumentException("Valor pago não condiz com valor do pedido!");
        }

        pedido.setStatus(StatusPedido.EM_PROCESSAMENTO);
        pedido.setItensPedido(carrinho.esvaziar());

        for (ItemPedido item: pedido.getItensPedido()){
            Estoque estoque = estoqueService.consultarEstoqueByIDQuadrinho(item.getIdQuadrinho());
            estoque.validarRetirada(item);

            QuadrinhoDTO quadrinho = quadrinhoService.consultarByID(item.getIdQuadrinho());
            item.alterarPreco(quadrinho.getPreco());
        }

        pedido.atualizarValorTotal();

        Pedido pedidoInserido = pedidoDAO.inserir(pedido);

        for (ItemPedido item : pedido.getItensPedido()) {
            item.setIdPedido(pedidoInserido.getId());
            ItemPedido itemInserido = itemPedidoDAO.inserir(item);
            pedidoInserido.getItensPedido().add(itemInserido);

            estoqueService.retirarDoEstoque(item);
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
            cupomService.inativar(cupom.getIdCupom());
        }

        return pedidoInserido;
    }

    public List<PedidoDTO> consultarTodos() {
        return pedidoDAO.consultarTodos();
    }

    public List<PedidoDTO> consultarPorIDCliente(int idCliente) {
        return pedidoDAO.consultarByIDCliente(idCliente);
    }

    public Pedido consultarByID(int id) {
		return pedidoDAO.consultarByID(id);
	}

    public Pedido atualizarStatus(AtualizarPedidoDTO atualizarPedidoDTO) {
        Pedido pedido = atualizarPedidoDTO.getPedido().getPedido();
        Pedido pedidoNoBanco = pedidoDAO.consultarByID(pedido.getId());

        StatusPedido status = pedido.getStatus();
        StatusPedido statusNoBanco = pedidoNoBanco.getStatus();

        List<PedidoPosVendaDTO> pedidosPosVenda = pedidoPosVendaDAO.consultarByIdPedido(pedido.getId());

        if (!pedidosPosVenda.isEmpty()){
            throw new IllegalArgumentException("Esse pedido já possui item com pedido de troca/devolução!");
        }

        if (statusNoBanco == StatusPedido.TROCA_CONCLUIDA || statusNoBanco == StatusPedido.DEVOLUCAO_CONCLUIDA){
            throw new IllegalArgumentException("Não é possível alterar pedido com troca ou devolução já concluída!");
        }

        if (status.equals(StatusPedido.TROCA_SOLICITADA) || status.equals(StatusPedido.DEVOLUCAO_SOLICITADA)){
            if (!pedidoNoBanco.getStatus().equals(StatusPedido.ENTREGUE)){
                throw new IllegalArgumentException("Não se pode pedir troca ou devolução se o pedido não foi entregue!");
            }
        }

        if (status.equals(StatusPedido.TROCA_CONCLUIDA) || status.equals(StatusPedido.DEVOLUCAO_CONCLUIDA)){
            List<ItemPedidoDTO> itensPedidoDTO = itemPedidoDAO.consultarByIDPedido(pedido.getId());
            List<ItemPedido> itensPedido = new ArrayList<>();
            
            for (ItemPedidoDTO itemPedidoDTO : itensPedidoDTO) {
                itensPedido.add(itemPedidoDTO.getItemPedido());
            }

            pedido.setItensPedido(itensPedido);

            cupomService.inserir(
                Cupom.gerarCupomTroca(
                    pedido.getIdCliente(),
                    pedido.calcularValorTotal()
                )
            );

            if (atualizarPedidoDTO.isRetornarAoEstoque()) {
                estoqueService.retornarAoEstoque(atualizarPedidoDTO.getPedido());
            }

        }

        return pedidoDAO.atualizarStatus(pedido);
    }

}
