package com.fatec.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fatec.controller.utils.ServletUtil;
import com.fatec.model.entidades.Cliente;
import com.fatec.service.ClienteService;

public class ClienteController extends HttpServlet {
    private static ClienteService clienteService = new ClienteService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        String parametroId = req.getParameter("id"); 
        
        try {

            if (parametroId == null){
                ServletUtil.retornarRespostaJson(
                    resp, 
                    clienteService.consultarTodos(),
                    HttpServletResponse.SC_OK
                );
                return;
            }

            int id = Integer.parseInt(parametroId);

            ServletUtil.retornarRespostaJson(
                resp,
                clienteService.consultarByID(id),
                HttpServletResponse.SC_OK
            );

        } catch (Exception e) {
            System.err.println(e.getMessage());
            ServletUtil.retornarResposta(resp, e.getMessage(), HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        Cliente clienteToAdd = ServletUtil.toCliente(req);

        try {
            clienteService.inserir(clienteToAdd);
            resp.setStatus(HttpServletResponse.SC_CREATED);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            ServletUtil.retornarResposta(resp, e.getMessage(), HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cliente clienteToUpdate = ServletUtil.toCliente(req);

        try {
            ServletUtil.retornarRespostaJson(
                resp,
                clienteService.atualizar(clienteToUpdate),
                HttpServletResponse.SC_OK
            );

        } catch (Exception e) {
            System.err.println(e.getMessage());
            ServletUtil.retornarResposta(resp, e.getMessage(), HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cliente clienteToDelete = ServletUtil.toCliente(req);

        try {
            clienteService.deletar(clienteToDelete);
            resp.setStatus(HttpServletResponse.SC_OK);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            ServletUtil.retornarResposta(resp, e.getMessage(), HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

}
