package com.esboco_comix.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.esboco_comix.controller.utils.ServletUtil;
import com.esboco_comix.model.entidades.Endereco;
import com.esboco_comix.service.EnderecoService;

public class EnderecoController extends HttpServlet {

    private static EnderecoService enderecoService = new EnderecoService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        try {

            String parametroIdCliente = req.getParameter("idcliente");

            if (parametroIdCliente != null){
                int id = Integer.parseInt(parametroIdCliente);

                ServletUtil.retornarRespostaJson(
                    resp,
                    enderecoService.consultarByIDCliente(id),
                    HttpServletResponse.SC_OK
                );
                return;
            }

            throw new Exception("Necessário id do cliente para consultar endereço!");

        } catch (Exception e) {
            ServletUtil.estourarErro(resp, e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Endereco enderecoToAdd = ServletUtil.jsonToObject(req, Endereco.class);
            ServletUtil.retornarRespostaJson(
                resp,
                enderecoService.inserir(enderecoToAdd),
                HttpServletResponse.SC_CREATED
            );
        } catch (Exception e) {
            ServletUtil.estourarErro(resp, e);
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        try {
            Endereco enderecoToUpdate = ServletUtil.jsonToObject(req, Endereco.class);
            ServletUtil.retornarRespostaJson(
                resp,
                enderecoService.atualizar(enderecoToUpdate),
                HttpServletResponse.SC_OK
            );
        } catch (Exception e) {
            ServletUtil.estourarErro(resp, e);
        }
    }
}
