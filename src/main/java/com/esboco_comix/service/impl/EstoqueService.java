package com.esboco_comix.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.esboco_comix.dao.impl.quadrinho.EstoqueDAO;
import com.esboco_comix.dto.EntradaEstoqueDTO;
import com.esboco_comix.dto.ItemPedidoDTO;
import com.esboco_comix.dto.PedidoDTO;
import com.esboco_comix.model.entidades.EntradaEstoque;
import com.esboco_comix.model.entidades.Estoque;
import com.esboco_comix.model.entidades.ItemPedido;

public class EstoqueService {

    private final EstoqueDAO estoqueDAO = new EstoqueDAO();

    public EntradaEstoque inserir(EntradaEstoque entradaEstoque) throws Exception {
        if (!(entradaEstoque.getQuantidade() > 0)){
            throw new Exception("Entrada no estoque deve ser maior que 0!");
        }

        return estoqueDAO.inserir(entradaEstoque);
    }

    public Estoque retornarAoEstoque(ItemPedidoDTO itemPedido) throws Exception {
        return estoqueDAO.retornarAoEstoque(itemPedido.getItemPedido());
    }

    public List<ItemPedidoDTO> retornarAoEstoque(PedidoDTO pedido) throws Exception {
        List<ItemPedidoDTO> lista = new ArrayList<>();

        for (ItemPedidoDTO item : pedido.getItensPedidoDTO()) {
            retornarAoEstoque(item);
            lista.add(item);
        }

        return lista;
    }

    public Estoque retirarDoEstoque(ItemPedido item) throws Exception {
        return estoqueDAO.retirarDoEstoque(item);
    }

    public Estoque consultarEstoqueByIDQuadrinho(int idQuadrinho) throws Exception {
        return estoqueDAO.consultarEstoqueByIDQuadrinho(idQuadrinho);
    }

    public void validarEstoque(ItemPedido item) throws Exception {
        Estoque estoque = consultarEstoqueByIDQuadrinho(item.getIdQuadrinho());

        int estoqueAtualizado = estoque.getQuantidadeTotal() - item.getQuantidade();
        if (estoqueAtualizado < 0){
            throw new Exception("Quantidade do item excede a quantidade no estoque!");
        }
    }

    public List<EntradaEstoqueDTO> consultarEntradasEstoque() throws Exception {
        return estoqueDAO.consultarEntradasEstoque();
    }
}
