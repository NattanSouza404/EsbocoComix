package com.esboco_comix.controller;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.esboco_comix.controller.utils.ServletUtil;
import com.esboco_comix.model.entidades.Cliente;
import com.esboco_comix.service.ClienteService;

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
        try {
            PedidoCadastrarCliente pedidoCadastrarCliente = ServletUtil.jsonToObject(req, PedidoCadastrarCliente.class);

            ServletUtil.retornarRespostaJson(
                resp,
                clienteService.inserir(pedidoCadastrarCliente),
                HttpServletResponse.SC_CREATED
            );
        } catch (Exception e) {
            ServletUtil.estourarErro(resp, e);
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            Cliente clienteToUpdate = ServletUtil.jsonToObject(req, Cliente.class);
            Cliente clienteAtualizado = null;

            String opcao = Optional.ofNullable(req.getParameter("opcao")).orElse("default");

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
        try {
            Cliente clienteToDelete = ServletUtil.jsonToObject(req, Cliente.class);
            clienteService.deletar(clienteToDelete);
            resp.setStatus(HttpServletResponse.SC_OK);
        } catch (Exception e) {
            ServletUtil.estourarErro(resp, e);
        }
    }

}
