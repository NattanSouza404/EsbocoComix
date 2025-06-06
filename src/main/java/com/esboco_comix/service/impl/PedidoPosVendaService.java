package com.esboco_comix.service.impl;

import com.esboco_comix.dao.impl.pedido.PedidoPosVendaDAO;
import com.esboco_comix.dto.ItemPedidoDTO;
import com.esboco_comix.dto.PedidoPosVendaDTO;
import com.esboco_comix.model.entidades.Cliente;
import com.esboco_comix.model.entidades.ItemPedido;
import com.esboco_comix.model.entidades.Pedido;
import com.esboco_comix.model.entidades.PedidoPosVenda;
import com.esboco_comix.model.enuns.StatusItemPedido;
import com.esboco_comix.model.enuns.StatusPedido;
import com.esboco_comix.service.impl.pedido.CalculadoraPedido;
import com.esboco_comix.service.impl.pedido.PedidoService;

import java.util.List;

public class PedidoPosVendaService {

    private final PedidoPosVendaDAO pedidoPosVendaDAO = new PedidoPosVendaDAO();
    private final CupomService cupomService = new CupomService();
    private final ClienteService clienteService = new ClienteService();
    private final EstoqueService estoqueService = new EstoqueService();
    private final PedidoService pedidoService = new PedidoService();

    public PedidoPosVendaDTO inserir(PedidoPosVenda pedido) throws Exception{
        return pedidoPosVendaDAO.inserir(pedido);
    }

    public List<PedidoPosVendaDTO> consultarTodos() throws Exception {
        return pedidoPosVendaDAO.consultarTodos();
    }

    public PedidoPosVendaDTO atualizarStatus(PedidoPosVendaDTO pedido) throws Exception {
        PedidoPosVenda pedidoPosVenda = pedido.getPedidoPosVenda();
        
        Pedido pedidoNoBanco = pedidoService.consultarByID(pedido.getPedidoPosVenda().getIdPedido());
        StatusPedido statusPedidoBanco = pedidoNoBanco.getStatus();
        
        if (statusPedidoBanco != StatusPedido.ENTREGUE){
            throw new Exception("Pedido não entregue ou pedido de troca/devolução dos itens já foi realizado!");
        }

        StatusItemPedido statusItemPedidoNoBanco = pedidoPosVendaDAO.consultarByID(pedidoPosVenda.getId()).getPedidoPosVenda().getStatus();

        if (statusItemPedidoNoBanco == StatusItemPedido.TROCA_CONCLUIDA || statusItemPedidoNoBanco == StatusItemPedido.DEVOLUCAO_CONCLUIDA){
            throw new Exception("Troca ou devolução do pedido já foi realizada.");
        }

        StatusItemPedido statusItemPedido = pedidoPosVenda.getStatus();
        if (statusItemPedido == StatusItemPedido.TROCA_CONCLUIDA || statusItemPedido == StatusItemPedido.DEVOLUCAO_CONCLUIDA){

            Cliente cliente = clienteService.consultarByIDPedido(pedidoPosVenda.getIdPedido());

            ItemPedido item = new ItemPedido();
            item.setIdPedido(pedidoPosVenda.getIdPedido());
            item.setIdQuadrinho(pedidoPosVenda.getIdQuadrinho());
            item.setQuantidade(pedidoPosVenda.getQuantidade());

            cupomService.gerarCupomTroca(
                cliente.getId(),
                new CalculadoraPedido(cupomService, new QuadrinhoService(), 
                new CartaoCreditoService()).calcularItemPedido(item)
            );

            ItemPedidoDTO itemPedidoDTO = new ItemPedidoDTO();
            itemPedidoDTO.setItemPedido(item);
            estoqueService.retornarAoEstoque(itemPedidoDTO);

        }

        return pedidoPosVendaDAO.atualizarStatus(pedido.getPedidoPosVenda());
    }

    public List<PedidoPosVendaDTO> consultarByIdPedido(int id) throws Exception{
        return pedidoPosVendaDAO.consultarByIdPedido(id);
    }

    public List<PedidoPosVendaDTO> consultarPorIDCliente(int id) throws Exception {
        return pedidoPosVendaDAO.consultarPorIDCliente(id);
    }

}
