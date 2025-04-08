package com.esboco_comix.controller.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.esboco_comix.controller.utils.AbstractController;
import com.esboco_comix.model.entidades.Endereco;
import com.esboco_comix.service.impl.EnderecoService;

public class EnderecoController extends AbstractController {

    private static EnderecoService enderecoService = new EnderecoService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        try {

            String parametroIdCliente = req.getParameter("idcliente");

            if (parametroIdCliente != null){
                int id = Integer.parseInt(parametroIdCliente);

                retornarRespostaJson(
                    resp,
                    enderecoService.consultarByIDCliente(id),
                    HttpServletResponse.SC_OK
                );
                return;
            }

        } catch (Exception e) {
            estourarErro(resp, e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Endereco enderecoToAdd = jsonToObject(req, Endereco.class);
            retornarRespostaJson(
                resp,
                enderecoService.inserir(enderecoToAdd),
                HttpServletResponse.SC_CREATED
            );
        } catch (Exception e) {
            estourarErro(resp, e);
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        try {
            Endereco enderecoToUpdate = jsonToObject(req, Endereco.class);
            retornarRespostaJson(
                resp,
                enderecoService.atualizar(enderecoToUpdate),
                HttpServletResponse.SC_OK
            );
        } catch (Exception e) {
            estourarErro(resp, e);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Endereco endereco = jsonToObject(req, Endereco.class);
            enderecoService.deletar(endereco);
            resp.setStatus(HttpServletResponse.SC_NO_CONTENT);
        } catch (Exception e) {
            estourarErro(resp, e);
        }
    }
}
