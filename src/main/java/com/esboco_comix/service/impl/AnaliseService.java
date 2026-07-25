package com.esboco_comix.service.impl;

import com.esboco_comix.dao.impl.analise.AnaliseDAO;
import com.esboco_comix.dto.AnaliseDTO;

import java.time.LocalDateTime;

public class AnaliseService {
    private final AnaliseDAO analiseDAO = new AnaliseDAO();

    public AnaliseDTO retornarAnalise(
        LocalDateTime dataInicio,
        LocalDateTime dataFinal
    ) {
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
        return AnaliseDTO.builder()
            .produtos(analiseDAO.consultarProdutos(dataInicio, dataFinal))
            .categorias(analiseDAO.consultarCategorias(dataInicio, dataFinal))
        .build();
    }
}
