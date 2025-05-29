package com.esboco_comix.controller.impl;

import com.esboco_comix.controller.utils.AbstractController;
import com.esboco_comix.dto.AlterarSenhaDTO;
import com.esboco_comix.dto.CadastrarClienteDTO;
import com.esboco_comix.model.entidades.Cliente;
import com.esboco_comix.service.impl.ClienteService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Optional;

public class ClienteController extends AbstractController {
    private final ClienteService clienteService = new ClienteService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
         
        try {
            String opcao = Optional.ofNullable(req.getParameter("opcao")).orElse("default");
            Object objetoResposta;

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
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
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
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        try {
            String opcao = Optional.ofNullable(req.getParameter("opcao")).orElse("default");
            Object objetoResposta;

            switch (opcao) {
                case "atualizarsenha":
                    AlterarSenhaDTO pedido = jsonToObject(req, AlterarSenhaDTO.class);
                    objetoResposta = clienteService.atualizarSenha(pedido);
                    break;

                case "atualizarstatuscadastro":
                    Cliente clienteToUpdateStatus = jsonToObject(req, Cliente.class);
                    objetoResposta = clienteService.atualizarStatusCadastro(clienteToUpdateStatus);
                    break;

                default:
                    Cliente clienteToUpdate = jsonToObject(req, Cliente.class);
                    objetoResposta = clienteService.atualizar(clienteToUpdate);
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
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            Cliente clienteToDelete = jsonToObject(req, Cliente.class);
            clienteService.deletar(clienteToDelete);
            resp.setStatus(HttpServletResponse.SC_OK);
        } catch (Exception e) {
            estourarErro(resp, e);
        }
    }

}
