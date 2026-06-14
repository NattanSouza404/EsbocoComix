package com.esboco_comix.service.impl;

import com.esboco_comix.dao.impl.analise.AnaliseDAO;
import com.esboco_comix.dto.ItemVendaDTO;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AnaliseService {
    private final AnaliseDAO analiseDAO = new AnaliseDAO();

    public Map<String, List<ItemVendaDTO>> retornarAnalise(LocalDateTime dataInicio, LocalDateTime dataFinal) {
        Map<String, List<ItemVendaDTO>> analise = new HashMap<>();

        if (dataInicio != null) {
            dataInicio = LocalDateTime.of(
                dataInicio.getYear(),
                dataInicio.getMonthValue(),
                dataInicio.getDayOfMonth(),
                0,
                0,
                0
            );
        }

        if (dataFinal != null) {
            dataFinal = LocalDateTime.of(
                dataFinal.getYear(),
                dataFinal.getMonthValue(),
                dataFinal.getDayOfMonth(),
                23,
                59,
                59
            );
        }

        analise.put("produtos", analiseDAO.consultarProdutos(dataInicio, dataFinal));
        analise.put("categorias", analiseDAO.consultarCategorias(dataInicio, dataFinal));

        return analise;
    }
}
