package com.esboco_comix.service.impl;

import java.util.List;

import com.esboco_comix.dao.impl.pedido.ItemPedidoDAO;
import com.esboco_comix.model.entidades.ItemPedido;

public class PedidoTrocaService {
    private ItemPedidoDAO itemPedidoDAO = new ItemPedidoDAO();
    
    public List<ItemPedido> consultarPedidosTroca() throws Exception{
        return itemPedidoDAO.consultarPedidosTroca();
    }

    public ItemPedido atualizarStatus(ItemPedido itemPedido) throws Exception {
        return itemPedidoDAO.atualizarStatus(itemPedido);
    }
}
