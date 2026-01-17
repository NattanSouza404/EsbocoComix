package com.esboco_comix.controller.impl;

import com.esboco_comix.controller.utils.AbstractController;
import com.esboco_comix.controller.utils.Router;
import com.esboco_comix.model.entidades.Endereco;
import com.esboco_comix.service.impl.EnderecoService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Map;

public class EnderecoController extends AbstractController {

    private final EnderecoService enderecoService = new EnderecoService();

    private final Router rotasGet = new Router(
        Map.of(
            "/por-id-cliente", this::consultarPorIdCliente
        )
    );

    private final Router rotasPost = new Router(
        Map.of(
            "", this::adicionar
        )
    );

    private final Router rotasPut = new Router(
        Map.of(
            "", this::atualizar
        )
    );

    private final Router rotasDelete = new Router(
        Map.of(
            "", this::deletar
        )
    );

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        processar(
            req,
            resp, 
            rotasGet, 
            HttpServletResponse.SC_OK,
            "Erro ao consultar endereço(s)"
        );
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        processar(
            req,
            resp, 
            rotasPost, 
            HttpServletResponse.SC_CREATED,
            "Erro ao adicionar endereço"
        );
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        processar(
            req,
            resp, 
            rotasPut, 
            HttpServletResponse.SC_OK,
            "Erro ao atualizar endereço"
        );
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        processar(
            req,
            resp, 
            rotasDelete, 
            HttpServletResponse.SC_NO_CONTENT,
            "Erro ao atualizar endereço"
        );
    }

    private Object consultarPorIdCliente(HttpServletRequest req) throws Exception {
        int idCliente = Integer.parseInt(
            requireParam(req, "id")
        );

        return enderecoService.consultarByIDCliente(idCliente);
    }

    private Object adicionar(HttpServletRequest req) throws Exception {
        Endereco enderecoToAdd = jsonToObject(req, Endereco.class);
        return enderecoService.inserir(enderecoToAdd);
    }

    private Object atualizar(HttpServletRequest req) throws Exception {
        Endereco enderecoToUpdate = jsonToObject(req, Endereco.class);
        return enderecoService.atualizar(enderecoToUpdate);
    }

    private Object deletar(HttpServletRequest req) throws Exception {
        Endereco endereco = jsonToObject(req, Endereco.class);
        enderecoService.inativar(endereco);
        return null;
    }
}
