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
         
        try {
            String parametroId = req.getParameter("id");

            if (parametroId != null){
                int id = Integer.parseInt(parametroId);

                ServletUtil.retornarRespostaJson(
                    resp,
                    clienteService.consultarByID(id),
                    HttpServletResponse.SC_OK
                );
                return;
            }

            ServletUtil.retornarRespostaJson(
                resp, 
                clienteService.consultarTodos(),
                HttpServletResponse.SC_OK
            ); 

        } catch (Exception e) {
            ServletUtil.estourarErro(resp, e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        Cliente clienteToAdd = ServletUtil.toCliente(req);

        try {
            ServletUtil.retornarRespostaJson(
                resp,
                clienteService.inserir(clienteToAdd),
                HttpServletResponse.SC_CREATED
            );
        } catch (Exception e) {
            ServletUtil.estourarErro(resp, e);
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cliente clienteToUpdate = ServletUtil.toCliente(req);

        String opcao = req.getParameter("opcao");

        try {
            Cliente clienteAtualizado = null;

            if (opcao == null){
                clienteAtualizado = clienteService.atualizar(clienteToUpdate);
                return;
            }

            switch (opcao) {
                case "atualizarsenha":
                    clienteAtualizado = clienteService.atualizarSenha(clienteToUpdate);
                    break;
            
                default:
                    clienteAtualizado = clienteService.atualizar(clienteToUpdate);
                    break;
            }

            ServletUtil.retornarRespostaJson(
                resp,
                clienteAtualizado,
                HttpServletResponse.SC_OK
            );

        } catch (Exception e) {
            ServletUtil.estourarErro(resp, e);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cliente clienteToDelete = ServletUtil.toCliente(req);

        try {
            clienteService.deletar(clienteToDelete);
            resp.setStatus(HttpServletResponse.SC_OK);
        } catch (Exception e) {
            ServletUtil.estourarErro(resp, e);
        }
    }

}
