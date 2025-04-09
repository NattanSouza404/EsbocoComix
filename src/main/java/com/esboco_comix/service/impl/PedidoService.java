package com.esboco_comix.service.impl;

import java.util.List;

import com.esboco_comix.dao.impl.pedido.ItemPedidoDAO;
import com.esboco_comix.dao.impl.pedido.PedidoDAO;
import com.esboco_comix.model.carrinho.Carrinho;
import com.esboco_comix.model.entidades.ItemPedido;
import com.esboco_comix.model.entidades.Pedido;
import com.esboco_comix.model.entidades.StatusPedido;

public class PedidoService {

    private PedidoDAO pedidoDAO = new PedidoDAO();
    private ItemPedidoDAO itemPedidoDAO = new ItemPedidoDAO();

    public Pedido inserir(Pedido pedido, Carrinho carrinho) throws Exception {
        pedido.setStatus(StatusPedido.APROVADO);
        pedido.setItensPedido(carrinho.getItensPedido());
        
        Pedido pedidoInserido = pedidoDAO.inserir(pedido);

        for (ItemPedido item : pedido.getItensPedido()) {
            item.setIdPedido(pedidoInserido.getId());
            ItemPedido itemInserido = itemPedidoDAO.inserir(item);
            pedidoInserido.getItensPedido().add(itemInserido);
        }

        carrinho.esvaziar();

        return pedidoInserido;
    }

    public List<Pedido> consultarTodos() throws Exception {
        List<Pedido> pedidos = pedidoDAO.consultarTodos(); 

        for (Pedido p : pedidos) {
            p.setItensPedido(itemPedidoDAO.consultarByIDPedido(p.getId()));
        }

        return pedidos;
    }

    public List<Pedido> consultarPorIDCliente(int idCliente) throws Exception{
        List<Pedido> pedidos = pedidoDAO.consultarByIDCliente(idCliente); 

        for (Pedido p : pedidos) {
            p.setItensPedido(itemPedidoDAO.consultarByIDPedido(p.getId()));
        }

        return pedidos;
    }

}
