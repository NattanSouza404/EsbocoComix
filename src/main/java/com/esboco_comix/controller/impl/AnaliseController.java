package com.esboco_comix.controller.impl;

import com.esboco_comix.controller.utils.AbstractController;
import com.esboco_comix.service.impl.AnaliseService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class AnaliseController extends AbstractController {
    private final AnaliseService analiseService = new AnaliseService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            retornarRespostaJson(
                    resp,
                    analiseService.retornarAnalise(),
                    HttpServletResponse.SC_OK
            );

        } catch (Exception e) {
            estourarErro(resp, e);
        }
    }
}
