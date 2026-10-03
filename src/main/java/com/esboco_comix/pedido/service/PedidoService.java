package com.esboco_comix.pedido.service;

import java.util.List;

import com.esboco_comix.cupom.dao.CupomDAO;
import com.esboco_comix.cupom.dominio.Cupom;
import com.esboco_comix.estoque.dao.EstoqueDAO;
import com.esboco_comix.pedido.dao.ItemPedidoDAO;
import com.esboco_comix.pedido.dao.PedidoDAO;
import com.esboco_comix.pedido.dao.PedidoPosVendaDAO;
import com.esboco_comix.pedido.dominio.ItemPedido;
import com.esboco_comix.pedido.dominio.Pedido;
import com.esboco_comix.pedido.dto.AtualizarPedidoDTO;
import com.esboco_comix.pedido.dto.PedidoDTO;
import com.esboco_comix.pedido.dto.PedidoPosVendaDTO;

public class PedidoService {

    private final EstoqueDAO estoqueDAO = new EstoqueDAO();

    private final PedidoDAO pedidoDAO = new PedidoDAO();
    private final ItemPedidoDAO itemPedidoDAO = new ItemPedidoDAO();
    private final CupomDAO cupomDAO = new CupomDAO();

    private final PedidoPosVendaDAO pedidoPosVendaDAO = new PedidoPosVendaDAO();

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
            List<ItemPedido> itensPedido =
                itemPedidoDAO.consultarByIDPedido(pedido.getId())
                    .stream()
                    .map(i -> i.getItemPedido())
                .toList();

            pedido.setItensPedido(itensPedido);

            cupomDAO.inserir(
                Cupom.gerarCupomTroca(
                    pedido.getIdCliente(),
                    pedido.calcularValorTotal()
                )
            );

            if (dto.retornarAoEstoque()) {
                for (ItemPedido item: pedido.getItensPedido()){
                    estoqueDAO.retornarAoEstoque(item);
                }
            }
        }

        return pedidoDAO.atualizarStatus(pedido);
    }

}
