package com.fatec.controller.utils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fatec.utils.ConversorJson;

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

    public static <T> T jsonToObject(HttpServletRequest req, Class<T> classe) throws Exception {
        return ConversorJson.jsonToObject(
            bodyRequestToString(req), classe
        );
    }

    public static void estourarErro(HttpServletResponse resp, Exception e) throws IOException {
        System.err.println(e.getMessage());

        Map<Object, Object> mapaErro = new HashMap<>();
        mapaErro.put("erro", e.getMessage());

        retornarResposta(resp, ConversorJson.mapToJson(mapaErro), HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
    }

    private static String bodyRequestToString(HttpServletRequest req) throws IOException {
        StringBuilder string = new StringBuilder();

        req.getReader().lines().forEach(
            s -> string.append(s)
        );
        
        return string.toString();
    }

}
