package com.esboco_comix.controller.impl;

import com.esboco_comix.controller.utils.AbstractController;
import com.esboco_comix.service.impl.QuadrinhoService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Optional;

public class QuadrinhoController extends AbstractController {

    private final QuadrinhoService quadrinhoService = new QuadrinhoService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
         
        try {
            String opcao = Optional.ofNullable(req.getParameter("opcao")).orElse("default");
            Object objetoResposta;

            switch (opcao) {
                case "consultarporfiltro":
                    objetoResposta = quadrinhoService.filtrarTodos(req);
                    break;

                case "consultarcategorias":
                    objetoResposta = quadrinhoService.consultarTodasCategorias();
                    break;

                default:
                    String parametroId = req.getParameter("id");

                    if (parametroId != null){
                        int id = Integer.parseInt(parametroId);
                        objetoResposta = quadrinhoService.consultarByID(id);
                        break;
                    }

                    objetoResposta = quadrinhoService.consultarTodos();

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
}
