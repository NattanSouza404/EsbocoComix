package com.esboco_comix.controller.impl;

import com.esboco_comix.controller.utils.AbstractController;
import com.esboco_comix.controller.utils.Router;
import com.esboco_comix.dto.AlterarSenhaDTO;
import com.esboco_comix.dto.CadastrarClienteDTO;
import com.esboco_comix.model.entidades.Cliente;
import com.esboco_comix.service.impl.ClienteService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Map;

public class ClienteController extends AbstractController {
    private final ClienteService clienteService = new ClienteService();

    private final Router rotasGet = new Router(
        Map.of(
            "", this::consultarTodos,
            "/id", this::consultarPorId,
            "/filtrar", this::consultarPorFiltro
        )
    );

    private final Router rotasPost = new Router(
        Map.of(
            "", this::cadastrarCliente
        )
    );

    private final Router rotasPut = new Router(
        Map.of(
            "", this::atualizarCliente,
            "/atualizar-senha", this::atualizarSenha,
            "/atualizar-status-cadastro", this::atualizarStatusCadastro
        )
    );

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        processar(
            req,
            resp,
            rotasGet,
            HttpServletResponse.SC_OK,
            "Erro ao consultar cliente(s)"
        );
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        processar(
            req,
            resp,
            rotasPost,
            HttpServletResponse.SC_CREATED,
            "Erro ao adicionar cliente(s)"
        );
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        processar(
            req,
            resp,
            rotasPut,
            HttpServletResponse.SC_OK,
            "Erro ao atualizar cliente"
        );
    }

    private Object consultarTodos(HttpServletRequest req){
        return clienteService.consultarTodos();
    }

    private Object consultarPorId(HttpServletRequest req){
        String parametroId = req.getParameter("id");

        if (parametroId != null){
            int id = Integer.parseInt(parametroId);
            return clienteService.consultarByID(id);
        }

        throw new IllegalArgumentException("Id de cliente não incluído");
    }

    private Object consultarPorFiltro(HttpServletRequest req){
        return clienteService.consultarTodos(req);
    }

    private Object cadastrarCliente(HttpServletRequest req) throws Exception {
        CadastrarClienteDTO pedidoCadastrarCliente = jsonToObject(req, CadastrarClienteDTO.class);
        return clienteService.inserir(pedidoCadastrarCliente);
    }

    public Object atualizarCliente(HttpServletRequest req) throws Exception {
        Cliente clienteToUpdate = jsonToObject(req, Cliente.class);
        return clienteService.atualizar(clienteToUpdate);
    }

    public Object atualizarSenha(HttpServletRequest req) throws Exception {
        AlterarSenhaDTO pedido = jsonToObject(req, AlterarSenhaDTO.class);
        return clienteService.atualizarSenha(pedido);
    }

    public Object atualizarStatusCadastro(HttpServletRequest req) throws Exception {
        Cliente clienteToUpdateStatus = jsonToObject(req, Cliente.class);
        return clienteService.atualizarStatusCadastro(clienteToUpdateStatus);
    }

}
