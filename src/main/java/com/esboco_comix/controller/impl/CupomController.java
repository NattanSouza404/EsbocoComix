package com.esboco_comix.controller.impl;

import com.esboco_comix.controller.utils.AbstractController;
import com.esboco_comix.model.entidades.Cupom;
import com.esboco_comix.service.impl.CupomService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class CupomController extends AbstractController {

    private final CupomService cupomService = new CupomService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {

            String parametroIdCliente = req.getParameter("idcliente");

            if (parametroIdCliente != null){
                int id = Integer.parseInt(parametroIdCliente);

                retornarRespostaJson(
                    resp,
                    cupomService.consultarByIDCliente(id),
                    HttpServletResponse.SC_OK
                );
            }

        } catch (Exception e) {
            estourarErro(resp, e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            Cupom cupom = jsonToObject(req, Cupom.class);

            retornarRespostaJson(
                resp,
                cupomService.inserir(cupom),
                HttpServletResponse.SC_CREATED
            );
        } catch (Exception e) {
            estourarErro(resp, e);
        }
    }
}
