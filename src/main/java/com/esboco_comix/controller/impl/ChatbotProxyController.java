package com.esboco_comix.controller.impl;

import java.io.IOException;
import java.util.Optional;

import com.esboco_comix.controller.utils.AbstractController;
import com.esboco_comix.service.impl.ChatbotProxyService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ChatbotProxyController extends AbstractController {

    private final ChatbotProxyService proxyService = new ChatbotProxyService();

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        try {
            String opcao = Optional.ofNullable(req.getParameter("opcao")).orElse("default");
            Object objetoResposta;

            switch (opcao) {
                case "get-message":
                    objetoResposta = proxyService.forwardJson(req);
                    break;
            
                default:
                    throw new IllegalArgumentException("Serviço não encontrado.");
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

}
