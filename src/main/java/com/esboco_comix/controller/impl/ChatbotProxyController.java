package com.esboco_comix.controller.impl;

import java.io.IOException;
import java.util.Map;

import com.esboco_comix.client.ChatbotProxyClient;
import com.esboco_comix.controller.utils.AbstractController;
import com.esboco_comix.controller.utils.Router;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ChatbotProxyController extends AbstractController {

    private final ChatbotProxyClient service = new ChatbotProxyClient();

    private final Router rotasPost = new Router(
        Map.of(
            "/prompt-mensagem", this::promptMensagem
        )
    );

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        processar(
            req,
            resp,
            rotasPost,
            HttpServletResponse.SC_OK,
            "Serviço não encontrado"
        );
    }

    private Object promptMensagem(HttpServletRequest req) throws Exception {
        return service.criarPrompt(req);
    }

}
