package com.esboco_comix.controller.impl;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.esboco_comix.controller.utils.AbstractController;
import com.esboco_comix.dto.AlterarSenhaDTO;
import com.esboco_comix.dto.CadastrarClienteDTO;
import com.esboco_comix.model.entidades.Cliente;
import com.esboco_comix.service.impl.ClienteService;

public class ClienteController extends AbstractController {
    private static ClienteService clienteService = new ClienteService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
         
        try {
            String opcao = Optional.ofNullable(req.getParameter("opcao")).orElse("default");
            Object objetoResposta = null;

            switch (opcao) {
                case "consultarporfiltro":
                    objetoResposta = clienteService.consultarTodos(req);
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
            CadastrarClienteDTO pedidoCadastrarCliente = jsonToObject(req, CadastrarClienteDTO.class);

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
                    AlterarSenhaDTO pedido = jsonToObject(req, AlterarSenhaDTO.class);
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
