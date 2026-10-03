package com.esboco_comix.estoque.service;

import java.util.List;

import com.esboco_comix.estoque.dao.EstoqueDAO;
import com.esboco_comix.estoque.dominio.EntradaEstoque;
import com.esboco_comix.estoque.dto.EntradaEstoqueDTO;

public class EstoqueService {

    private final EstoqueDAO estoqueDAO = new EstoqueDAO();

    public EntradaEstoque inserir(EntradaEstoque entradaEstoque) {
        entradaEstoque.validar();

        return estoqueDAO.inserir(entradaEstoque);
    }

    public List<EntradaEstoqueDTO> consultarEntradasEstoque() {
        return estoqueDAO.consultarEntradasEstoque();
    }
}
