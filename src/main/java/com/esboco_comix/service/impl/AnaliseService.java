package com.esboco_comix.service.impl;

import com.esboco_comix.dao.impl.analise.AnaliseDAO;
import com.esboco_comix.dto.FiltroAnaliseDTO;
import com.esboco_comix.dto.ItemVendaDTO;

import jakarta.servlet.http.HttpServletRequest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AnaliseService {
    private final AnaliseDAO analiseDAO = new AnaliseDAO();

    public Map<String, List<ItemVendaDTO>> retornarAnalise(HttpServletRequest req) {
        Map<String, List<ItemVendaDTO>> analise = new HashMap<>();

        FiltroAnaliseDTO filtro = new FiltroAnaliseDTO();

        String dataInicio = req.getParameter("dataInicio");
        String dataFinal = req.getParameter("dataFinal");

        if (dataInicio != null && !dataInicio.isBlank()){
            LocalDate data = LocalDate.parse(dataInicio);
            
            filtro.setDataInicio(
                LocalDateTime.of(data.getYear(), data.getMonthValue(), data.getDayOfMonth(), 0, 0, 0)
            );
        }

        if (dataFinal != null && !dataFinal.isBlank()){
            LocalDate data = LocalDate.parse(dataFinal);

            filtro.setDataFinal(
                LocalDateTime.of(data.getYear(), data.getMonthValue(), data.getDayOfMonth(), 23, 59, 59)
            );
        }

        analise.put("produtos", analiseDAO.consultarProdutos(filtro));
        analise.put("categorias", analiseDAO.consultarCategorias(filtro));

        return analise;
    }
}
