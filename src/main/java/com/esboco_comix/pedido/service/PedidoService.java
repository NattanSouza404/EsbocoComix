package com.esboco_comix.pedido.service;

import java.util.ArrayList;
import java.util.List;

import com.esboco_comix.carrinho.dominio.Carrinho;
import com.esboco_comix.cliente.dominio.entidades.*;
import com.esboco_comix.cliente.service.CartaoCreditoService;
import com.esboco_comix.cupom.dominio.Cupom;
import com.esboco_comix.cupom.service.CupomService;
import com.esboco_comix.estoque.dominio.Estoque;
import com.esboco_comix.estoque.service.EstoqueService;
import com.esboco_comix.pedido.dao.CartaoCreditoPedidoDAO;
import com.esboco_comix.pedido.dao.CupomPedidoDAO;
import com.esboco_comix.pedido.dao.ItemPedidoDAO;
import com.esboco_comix.pedido.dao.PedidoDAO;
import com.esboco_comix.pedido.dao.PedidoPosVendaDAO;
import com.esboco_comix.pedido.dominio.CartaoCreditoPedido;
import com.esboco_comix.pedido.dominio.CupomPedido;
import com.esboco_comix.pedido.dominio.ItemPedido;
import com.esboco_comix.pedido.dominio.Pedido;
import com.esboco_comix.pedido.dominio.enuns.StatusPedido;
import com.esboco_comix.pedido.dto.AtualizarPedidoDTO;
import com.esboco_comix.pedido.dto.ItemPedidoDTO;
import com.esboco_comix.pedido.dto.PedidoDTO;
import com.esboco_comix.pedido.dto.PedidoPosVendaDTO;
import com.esboco_comix.quadrinho.dto.QuadrinhoDTO;
import com.esboco_comix.quadrinho.service.QuadrinhoService;

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

    public Pedido atualizarStatus(AtualizarPedidoDTO dto) {
        Pedido pedido = pedidoDAO.consultarByID(dto.id());

        List<PedidoPosVendaDTO> pedidosPosVenda = pedidoPosVendaDAO.consultarByIdPedido(pedido.getId());

        if (!pedidosPosVenda.isEmpty()){
            throw new IllegalArgumentException("Esse pedido já possui item com pedido de troca/devolução!");
        }

        pedido.alterarStatus(dto.status());

        if (pedido.comTrocaOuDevolucaoConcluida()){
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

            if (dto.retornarAoEstoque()) {
                var pedidoDTO = new PedidoDTO();
                pedidoDTO.setPedido(pedido);
                estoqueService.retornarAoEstoque(pedidoDTO);
            }
        }

        return pedidoDAO.atualizarStatus(pedido);
    }

}
