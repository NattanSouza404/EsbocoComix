package com.esboco_comix.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.esboco_comix.dao.impl.cliente.ClienteDAO;
import com.esboco_comix.dao.impl.pedido.ItemPedidoDAO;
import com.esboco_comix.dao.impl.pedido.PedidoDAO;
import com.esboco_comix.dao.impl.quadrinho.QuadrinhoDAO;
import com.esboco_comix.dto.ItemPedidoDTO;
import com.esboco_comix.model.entidades.Cliente;
import com.esboco_comix.model.entidades.ItemPedido;
import com.esboco_comix.model.entidades.Pedido;
import com.esboco_comix.model.entidades.Quadrinho;

public class PedidoTrocaService {
    private ItemPedidoDAO itemPedidoDAO = new ItemPedidoDAO();
    private QuadrinhoDAO quadrinhoDAO = new QuadrinhoDAO();
    private PedidoDAO pedidoDAO = new PedidoDAO();
    private ClienteDAO clienteDAO = new ClienteDAO();
    
    public List<ItemPedidoDTO> consultarPedidosTroca() throws Exception{
        List<ItemPedidoDTO> itens = new ArrayList<>();

        for (ItemPedido itemPedido : itemPedidoDAO.consultarPedidosTroca()) {
            Pedido pedido = pedidoDAO.consultarByID(itemPedido.getIdPedido());
            Cliente cliente = clienteDAO.consultarByID(pedido.getIdCliente());
            Quadrinho quadrinho = quadrinhoDAO.consultarByID(itemPedido.getIdQuadrinho());

            ItemPedidoDTO itemPedidoDTO = new ItemPedidoDTO();
            itemPedidoDTO.setItemPedido(itemPedido);

            itemPedidoDTO.setNomeCliente(cliente.getNome());
            itemPedidoDTO.setNomeQuadrinho(quadrinho.getTitulo());

            itens.add(itemPedidoDTO);
        }

        return itens;
    }

    public ItemPedido atualizarStatus(ItemPedido itemPedido) throws Exception {
        return itemPedidoDAO.atualizarStatus(itemPedido);
    }
}
