package com.fatec.controller.utils;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fatec.model.entidades.Cliente;

public class ServletUtil {

    public static void retornarRespostaJson(HttpServletResponse resp, Object object, int sc) throws IOException {
        resp.getWriter().println(
            ConversorJson.toJson(object)
        );
        resp.setStatus(sc);
    }

    public static void retornarResposta(HttpServletResponse resp, String string, int sc) throws IOException {
        resp.getWriter().println(string);
        resp.setStatus(sc);
    }

    public static String bodyRequestToString(HttpServletRequest req) throws IOException {
        StringBuilder string = new StringBuilder();

        req.getReader().lines().forEach(
            s -> string.append(s)
        );
        
        return string.toString();
    }

    public static Cliente toCliente(HttpServletRequest req) throws JsonMappingException, JsonProcessingException, IOException {
        return ConversorJson.toCliente(
            bodyRequestToString(req)
        );
    }

    public static void estourarErro(HttpServletResponse resp, Exception e, int scInternalServerError) throws IOException {
        System.err.println(e.getMessage());
        retornarResposta(resp, e.getMessage(), HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
    }

}
