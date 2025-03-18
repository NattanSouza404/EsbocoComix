package com.esboco_comix.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.esboco_comix.controller.pedidos.PedidoAlterarSenha;
import com.esboco_comix.controller.pedidos.PedidoCadastrarCliente;
import static com.esboco_comix.controller.utils.ServletUtil.*;
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
                    Map<String, String> parametrosFiltro = new HashMap<>();
                    parametrosFiltro.put("nome", req.getParameter("nome"));
                    parametrosFiltro.put("genero", req.getParameter("genero"));
                    parametrosFiltro.put("email", req.getParameter("email"));
                    parametrosFiltro.put("cpf", req.getParameter("cpf"));
                    parametrosFiltro.put("dataNascimento", req.getParameter("dataNascimento"));
                    parametrosFiltro.put("ranking", req.getParameter("ranking"));
                    parametrosFiltro.put("isAtivo", req.getParameter("isAtivo"));
                   
                    objetoResposta = clienteService.consultarTodos(parametrosFiltro);
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

            retornarRespostaJson(
                resp, 
                objetoResposta,
                HttpServletResponse.SC_OK
            ); 

        } catch (Exception e) {
            estourarErro(resp, e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            PedidoCadastrarCliente pedidoCadastrarCliente = jsonToObject(req, PedidoCadastrarCliente.class);

            retornarRespostaJson(
                resp,
                clienteService.inserir(pedidoCadastrarCliente),
                HttpServletResponse.SC_CREATED
            );
        } catch (Exception e) {
            estourarErro(resp, e);
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            String opcao = Optional.ofNullable(req.getParameter("opcao")).orElse("default");
            Object objetoResposta = null;

            switch (opcao) {
                default:
                    Cliente clienteToUpdate = jsonToObject(req, Cliente.class);
                    objetoResposta = clienteService.atualizar(clienteToUpdate);
                    break;

                case "atualizarsenha":
                    PedidoAlterarSenha pedido = jsonToObject(req, PedidoAlterarSenha.class);
                    objetoResposta = clienteService.atualizarSenha(pedido);
                    break;

                case "atualizarstatuscadastro":
                    Cliente clienteToUpdateStatus = jsonToObject(req, Cliente.class);
                    objetoResposta = clienteService.atualizarStatusCadastro(clienteToUpdateStatus);
                    break;
            }

            retornarRespostaJson(
                resp,
                objetoResposta,
                HttpServletResponse.SC_OK
            );
        } catch (Exception e) {
            estourarErro(resp, e);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Cliente clienteToDelete = jsonToObject(req, Cliente.class);
            clienteService.deletar(clienteToDelete);
            resp.setStatus(HttpServletResponse.SC_OK);
        } catch (Exception e) {
            estourarErro(resp, e);
        }
    }

}
