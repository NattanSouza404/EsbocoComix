package com.esboco_comix.analise.service;

import com.esboco_comix.analise.dao.AnaliseDAO;
import com.esboco_comix.analise.dto.AnaliseDTO;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class AnaliseService {
    private final AnaliseDAO analiseDAO = new AnaliseDAO();

    public AnaliseDTO retornarAnalise(
        LocalDate dataInicio,
        LocalDate dataFinal
    ) {
        LocalDateTime inicio = dataInicio != null
            ? dataInicio.atStartOfDay()
            : null;

        LocalDateTime fim = dataFinal != null
            ? dataFinal.atTime(23, 59, 59)
            : null;

        return AnaliseDTO.builder()
            .produtos(analiseDAO.consultarProdutos(inicio, fim))
            .categorias(analiseDAO.consultarCategorias(inicio, fim))
        .build();
    }
}
