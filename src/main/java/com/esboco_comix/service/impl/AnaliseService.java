package com.esboco_comix.service.impl;

import com.esboco_comix.dao.impl.analise.AnaliseDAO;
import com.esboco_comix.dto.ItemVendaDTO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AnaliseService {
    private final AnaliseDAO analiseDAO = new AnaliseDAO();

    public Map<String, List<ItemVendaDTO>> retornarAnalise() throws Exception {
        Map<String, List<ItemVendaDTO>> analise = new HashMap<>();

        analise.put("produtos", analiseDAO.consultarProdutos());
        analise.put("categorias", analiseDAO.consultarCategorias());

        return analise;
    }
}
