package com.esboco_comix.estoque.service;

import java.util.ArrayList;
import java.util.List;

import com.esboco_comix.estoque.dao.EstoqueDAO;
import com.esboco_comix.estoque.dominio.EntradaEstoque;
import com.esboco_comix.estoque.dominio.Estoque;
import com.esboco_comix.estoque.dto.EntradaEstoqueDTO;
import com.esboco_comix.pedido.dominio.ItemPedido;
import com.esboco_comix.pedido.dto.ItemPedidoDTO;
import com.esboco_comix.pedido.dto.PedidoDTO;

public class EstoqueService {

    private final EstoqueDAO estoqueDAO = new EstoqueDAO();

    public EntradaEstoque inserir(EntradaEstoque entradaEstoque) {
        entradaEstoque.validar();

        return estoqueDAO.inserir(entradaEstoque);
    }

    public Estoque retornarAoEstoque(ItemPedidoDTO itemPedido) {
        return estoqueDAO.retornarAoEstoque(itemPedido.getItemPedido());
    }

    public List<ItemPedidoDTO> retornarAoEstoque(PedidoDTO pedido) {
        List<ItemPedidoDTO> lista = new ArrayList<>();

        for (ItemPedido item : pedido.getPedido().getItensPedido()) {
            ItemPedidoDTO dto = new ItemPedidoDTO();
            dto.setItemPedido(item);
            
            retornarAoEstoque(dto);
            lista.add(dto);
        }

        return lista;
    }

    public Estoque retirarDoEstoque(ItemPedido item) {
        return estoqueDAO.retirarDoEstoque(item);
    }

    public Estoque consultarEstoqueByIDQuadrinho(int idQuadrinho) {
        return estoqueDAO.consultarEstoqueByIDQuadrinho(idQuadrinho);
    }

    public List<EntradaEstoqueDTO> consultarEntradasEstoque() {
        return estoqueDAO.consultarEntradasEstoque();
    }
}
