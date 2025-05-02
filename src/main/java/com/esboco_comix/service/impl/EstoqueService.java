package com.esboco_comix.service.impl;

import com.esboco_comix.dao.impl.quadrinho.EstoqueDAO;
import com.esboco_comix.model.entidades.EntradaEstoque;

public class EstoqueService {

    private EstoqueDAO estoqueDAO = new EstoqueDAO();

    public EntradaEstoque inserir(EntradaEstoque entradaEstoque) throws Exception {
        if (!(entradaEstoque.getQuantidade() > 0)){
            throw new Exception("Entrada no estoque deve ser maior que 0!");
        }

        return estoqueDAO.inserir(entradaEstoque);
    }

}
