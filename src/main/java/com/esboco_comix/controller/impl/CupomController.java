package com.esboco_comix.controller.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.esboco_comix.controller.utils.AbstractController;
import com.esboco_comix.service.impl.CupomService;

public class CupomController extends AbstractController {

    private CupomService cupomService = new CupomService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {

            String parametroIdCliente = req.getParameter("idcliente");

            if (parametroIdCliente != null){
                int id = Integer.parseInt(parametroIdCliente);

                retornarRespostaJson(
                    resp,
                    cupomService.consultarByIDCliente(id),
                    HttpServletResponse.SC_OK
                );
                return;
            }

        } catch (Exception e) {
            estourarErro(resp, e);
        }
    }
}
