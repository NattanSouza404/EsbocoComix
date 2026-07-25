package com.esboco_comix.pedido.service;

import com.esboco_comix.cupom.dominio.Cupom;
import com.esboco_comix.cupom.service.CupomService;
import com.esboco_comix.estoque.service.EstoqueService;
import com.esboco_comix.model.entidades.Cliente;
import com.esboco_comix.pedido.dao.ItemPedidoDAO;
import com.esboco_comix.pedido.dao.PedidoPosVendaDAO;
import com.esboco_comix.pedido.dominio.ItemPedido;
import com.esboco_comix.pedido.dominio.Pedido;
import com.esboco_comix.pedido.dominio.PedidoPosVenda;
import com.esboco_comix.pedido.dominio.enuns.StatusItemPedido;
import com.esboco_comix.pedido.dominio.enuns.StatusPedido;
import com.esboco_comix.pedido.dto.AtualizarPedidoPosVendaDTO;
import com.esboco_comix.pedido.dto.ItemPedidoDTO;
import com.esboco_comix.pedido.dto.PedidoPosVendaDTO;
import com.esboco_comix.service.impl.ClienteService;

import java.util.List;

public class PedidoPosVendaService {

    private final PedidoPosVendaDAO pedidoPosVendaDAO = new PedidoPosVendaDAO();
    private final CupomService cupomService = new CupomService();
    private final ClienteService clienteService = new ClienteService();
    private final EstoqueService estoqueService = new EstoqueService();
    private final PedidoService pedidoService = new PedidoService();

    private final ItemPedidoDAO itemPedidoDAO = new ItemPedidoDAO();

    public PedidoPosVendaDTO inserir(PedidoPosVenda pedido){
        ItemPedido item = itemPedidoDAO.consultarByID(pedido.getIdPedido(), pedido.getIdQuadrinho());
        
        Pedido pedidoNoBanco = pedidoService.consultarByID(pedido.getIdPedido());
        StatusPedido statusPedidoBanco = pedidoNoBanco.getStatus();
        
        if (statusPedidoBanco != StatusPedido.ENTREGUE){
            throw new IllegalArgumentException("Pedido não entregue ou pedido de troca/devolução dos itens já foi realizado!");
        }

        if (pedido.getQuantidade() < 1){
            throw new IllegalArgumentException("Quantidade da troca/devolução precisa ser maior que zero!");
        }

        if (pedido.getQuantidade() > item.getQuantidade()){
            throw new IllegalArgumentException("Quantidade de troca/devolução maior do que o limite!");
        }

        List<PedidoPosVendaDTO> pedidosPosVenda = pedidoPosVendaDAO.consultarByIdPedido(pedido.getIdPedido());

        int quantidadeTotalJaTrocadaOuDevoluida = 0;

        for (PedidoPosVendaDTO p : pedidosPosVenda){
            if (p.getPedidoPosVenda().getIdQuadrinho() == pedido.getIdQuadrinho()){
                quantidadeTotalJaTrocadaOuDevoluida += p.getPedidoPosVenda().getQuantidade();
            }
        }

        int limite = quantidadeTotalJaTrocadaOuDevoluida + pedido.getQuantidade();

        if (item.getQuantidade() < limite ){
            throw new IllegalArgumentException("Não é possível realizar mais trocas ou devoluções desse item!");
        }

        return pedidoPosVendaDAO.inserir(pedido);
    }

    public List<PedidoPosVendaDTO> consultarTodos() {
        return pedidoPosVendaDAO.consultarTodos();
    }

    public PedidoPosVendaDTO atualizarStatus(AtualizarPedidoPosVendaDTO atualizarPedido) {
        PedidoPosVenda pedidoPosVenda = atualizarPedido.getPedidoPosVenda();
        
        Pedido pedidoNoBanco = pedidoService.consultarByID(atualizarPedido.getPedidoPosVenda().getIdPedido());
        StatusPedido statusPedidoBanco = pedidoNoBanco.getStatus();
        
        if (statusPedidoBanco != StatusPedido.ENTREGUE){
            throw new IllegalArgumentException("Pedido não entregue ou pedido de troca/devolução dos itens já foi realizado!");
        }

        StatusItemPedido statusItemPedidoNoBanco = pedidoPosVendaDAO.consultarByID(pedidoPosVenda.getId()).getPedidoPosVenda().getStatus();

        if (statusItemPedidoNoBanco == StatusItemPedido.TROCA_CONCLUIDA || statusItemPedidoNoBanco == StatusItemPedido.DEVOLUCAO_CONCLUIDA){
            throw new IllegalArgumentException("Troca ou devolução do pedido já foi realizada.");
        }

        StatusItemPedido statusItemPedido = pedidoPosVenda.getStatus();
        if (statusItemPedido == StatusItemPedido.TROCA_CONCLUIDA || statusItemPedido == StatusItemPedido.DEVOLUCAO_CONCLUIDA){

            Cliente cliente = clienteService.consultarByIDPedido(pedidoPosVenda.getIdPedido());

            ItemPedido item = ItemPedido.builder()
                .idPedido(pedidoPosVenda.getIdPedido())
                .idQuadrinho(pedidoPosVenda.getIdQuadrinho())
                .quantidade(pedidoPosVenda.getQuantidade())
            .build();

            cupomService.inserir(
                Cupom.gerarCupomTroca(
                    cliente.getId(),
                    item.calcularValor()
                )
            );

            ItemPedidoDTO itemPedidoDTO = new ItemPedidoDTO();
            itemPedidoDTO.setItemPedido(item);

            if (atualizarPedido.isRetornarAoEstoque()){
                estoqueService.retornarAoEstoque(itemPedidoDTO);
            }
        }

        return pedidoPosVendaDAO.atualizarStatus(atualizarPedido.getPedidoPosVenda());
    }

    public List<PedidoPosVendaDTO> consultarPorIDCliente(int id) {
        return pedidoPosVendaDAO.consultarPorIDCliente(id);
    }

}
