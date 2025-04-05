package com.esboco_comix.controller;

import static com.esboco_comix.controller.utils.ServletUtil.*;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.esboco_comix.service.QuadrinhoService;

public class QuadrinhoController extends HttpServlet {

    private QuadrinhoService quadrinhoService = new QuadrinhoService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
         
        try {
            String opcao = Optional.ofNullable(req.getParameter("opcao")).orElse("default");
            Object objetoResposta = null;

            switch (opcao) {
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
