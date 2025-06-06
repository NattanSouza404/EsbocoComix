package com.esboco_comix.service.impl.pedido;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.esboco_comix.dao.impl.pedido.*;
import jakarta.servlet.http.HttpSession;

import com.esboco_comix.controller.session.Carrinho;
import com.esboco_comix.dto.ItemPedidoDTO;
import com.esboco_comix.dto.PedidoDTO;
import com.esboco_comix.dto.PedidoPosVendaDTO;
import com.esboco_comix.model.entidades.*;
import com.esboco_comix.model.enuns.StatusPedido;
import com.esboco_comix.service.impl.CarrinhoService;
import com.esboco_comix.service.impl.CartaoCreditoService;
import com.esboco_comix.service.impl.CupomService;
import com.esboco_comix.service.impl.EstoqueService;
import com.esboco_comix.service.impl.QuadrinhoService;
import com.esboco_comix.service.validador.impl.FormaPagamentoValidador;

public class PedidoService {

    private final CarrinhoService carrinhoService = new CarrinhoService();
    private final CupomService cupomService = new CupomService();
    private final QuadrinhoService quadrinhoService = new QuadrinhoService();
    private final CartaoCreditoService cartaoCreditoService = new CartaoCreditoService();
    private final EstoqueService estoqueService = new EstoqueService();
    
    private final PedidoDAO pedidoDAO = new PedidoDAO();
    private final ItemPedidoDAO itemPedidoDAO = new ItemPedidoDAO();
    private final CartaoCreditoPedidoDAO cartaoCreditoPedidoDAO = new CartaoCreditoPedidoDAO();
    private final CupomPedidoDAO cupomPedidoDAO = new CupomPedidoDAO();

    private final PedidoPosVendaDAO pedidoPosVendaDAO = new PedidoPosVendaDAO();
    
    private final CalculadoraPedido calculadora = new CalculadoraPedido(
        this.cupomService,
        this.quadrinhoService,
        this.cartaoCreditoService
    );

    private final FormaPagamentoValidador validador = new FormaPagamentoValidador(calculadora);

    public Pedido inserir(Pedido pedido, HttpSession session) throws Exception {

        Carrinho carrinho = carrinhoService.retornarCarrinhoSessao(session);

        if (carrinho.isVazio()) {
            throw new Exception("Nenhum item presente no carrinho!");
        }

        pedido.setItensPedido(carrinho.getItensPedido());

        validador.validar(pedido);

        pedido.setStatus(StatusPedido.EM_PROCESSAMENTO);
        pedido.setItensPedido(carrinho.esvaziar());
        pedido.setValorTotal(calculadora.calcularValorTotalPedido(pedido, pedido.getItensPedido()));

        for (ItemPedido item: pedido.getItensPedido()){
            estoqueService.validarEstoque(item);
        }

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

    public Map<String, Object> consultarTodos() throws Exception {
        Map<String, Object> consulta = new HashMap<>();

        consulta.put("pedidos", pedidoDAO.consultarTodos());
        consulta.put("itensPedido", itemPedidoDAO.consultarPedidosTroca());

        return consulta;
    }

    public List<PedidoDTO> consultarPorIDCliente(int idCliente) throws Exception {
        return pedidoDAO.consultarByIDCliente(idCliente);
    }

    public Pedido consultarByID(int id) throws Exception {
		return pedidoDAO.consultarByID(id);
	}

    public Pedido atualizarStatus(PedidoDTO pedidoDTO) throws Exception {
        Pedido pedido = pedidoDTO.getPedido();
        Pedido pedidoNoBanco = pedidoDAO.consultarByID(pedido.getId());

        StatusPedido status = pedido.getStatus();
        StatusPedido statusNoBanco = pedidoNoBanco.getStatus();

        List<PedidoPosVendaDTO> pedidosPosVenda = pedidoPosVendaDAO.consultarByIdPedido(pedido.getId());

        if (!pedidosPosVenda.isEmpty()){
            throw new Exception("Esse pedido já possui item com pedido de troca/devolução!");
        }

        if (statusNoBanco == StatusPedido.TROCA_CONCLUIDA || statusNoBanco == StatusPedido.DEVOLUCAO_CONCLUIDA){
            throw new Exception("Não é possível alterar pedido com troca ou devolução já concluída!");
        }

        if (status.equals(StatusPedido.TROCA_SOLICITADA) || status.equals(StatusPedido.DEVOLUCAO_SOLICITADA)){
            if (!pedidoNoBanco.getStatus().equals(StatusPedido.ENTREGUE)){
                throw new Exception("Não se pode pedir troca ou devolução se o pedido não foi entregue!");
            }
        }

        if (status.equals(StatusPedido.TROCA_CONCLUIDA) || status.equals(StatusPedido.DEVOLUCAO_CONCLUIDA)){
            List<ItemPedidoDTO> itensPedidoDTO = itemPedidoDAO.consultarByIDPedido(pedido.getId());
            List<ItemPedido> itensPedido = new ArrayList<>();
            
            for (ItemPedidoDTO itemPedidoDTO : itensPedidoDTO) {
                itensPedido.add(itemPedidoDTO.getItemPedido());
            }

            cupomService.gerarCupomTroca(
                pedido.getIdCliente(),
                calculadora.calcularValorTotalPedido(pedido, itensPedido)
            );

            estoqueService.retornarAoEstoque(pedidoDTO);
        }

        return pedidoDAO.atualizarStatus(pedido);
    }

}
