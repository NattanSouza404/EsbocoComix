package com.esboco_comix.controller;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.esboco_comix.controller.pedidos.PedidoAlterarSenha;
import com.esboco_comix.controller.pedidos.PedidoCadastrarCliente;
import com.esboco_comix.controller.utils.ServletUtil;
import com.esboco_comix.model.entidades.Cliente;
import com.esboco_comix.service.ClienteService;

public class ClienteController extends HttpServlet {
    private static ClienteService clienteService = new ClienteService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
         
        try {
            String opcao = Optional.ofNullable(req.getParameter("opcao")).orElse("default");
            Object objetoResposta = null;

            switch (opcao) {
                case "consultarporfiltro":
                    Cliente filtro = ServletUtil.jsonToObject(req, Cliente.class);
                    objetoResposta = clienteService.consultarTodos(filtro);
                    break;
            
                default:
                    String parametroId = req.getParameter("id");

                    if (parametroId != null){
                        int id = Integer.parseInt(parametroId);
                        objetoResposta = clienteService.consultarByID(id);
                        break;
                    }

                    objetoResposta = clienteService.consultarTodos();

                    break;
            }

            ServletUtil.retornarRespostaJson(
                resp, 
                objetoResposta,
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
            String opcao = Optional.ofNullable(req.getParameter("opcao")).orElse("default");
            Object objetoResposta = null;

            switch (opcao) {
                default:
                    Cliente clienteToUpdate = ServletUtil.jsonToObject(req, Cliente.class);
                    objetoResposta = clienteService.atualizar(clienteToUpdate);
                    break;

                case "atualizarsenha":
                    PedidoAlterarSenha pedido = ServletUtil.jsonToObject(req, PedidoAlterarSenha.class);
                    objetoResposta = clienteService.atualizarSenha(pedido);
                    break;

                case "atualizarstatuscadastro":
                    Cliente clienteToUpdateStatus = ServletUtil.jsonToObject(req, Cliente.class);
                    objetoResposta = clienteService.atualizarStatusCadastro(clienteToUpdateStatus);
                    break;
            }

            ServletUtil.retornarRespostaJson(
                resp,
                objetoResposta,
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
