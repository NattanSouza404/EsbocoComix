package com.esboco_comix.controller.impl;

import com.esboco_comix.controller.utils.AbstractController;
import com.esboco_comix.controller.utils.Router;
import com.esboco_comix.service.impl.AnaliseService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Map;

public class AnaliseController extends AbstractController {
    private final AnaliseService analiseService = new AnaliseService();

    private final Router rotasGet = new Router(
        Map.of(
            "", this::retornarAnalise
        )
    );

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        processar(
            req,
            resp,
            rotasGet,
            HttpServletResponse.SC_OK,
            "Erro buscando dados para análise"
        );
    }

    private Object retornarAnalise(HttpServletRequest req) throws Exception {
        String dataInicio = req.getParameter("dataInicio");
        String dataFinal = req.getParameter("dataFinal");

        return analiseService.retornarAnalise(
            dataInicio != null ? LocalDateTime.parse(dataInicio) : null,
            dataFinal != null ? LocalDateTime.parse(dataFinal) : null
        );
    }
}
