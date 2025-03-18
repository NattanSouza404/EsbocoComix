package com.esboco_comix.controller.utils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.esboco_comix.utils.ConversorJson;

public class ServletUtil {

    public static void retornarRespostaJson(HttpServletResponse resp, Object object, int sc) throws IOException {
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json; charset=UTF-8");
        resp.getWriter().println(
            ConversorJson.toJson(object)
        );
        resp.setStatus(sc);
    }

    public static <T> T jsonToObject(HttpServletRequest req, Class<T> classe) throws Exception {
        return ConversorJson.jsonToObject(
            bodyRequestToString(req), classe
        );
    }

    public static void estourarErro(HttpServletResponse resp, Exception e) throws IOException {
        System.err.println(e.getMessage());
        e.printStackTrace();

        Map<Object, Object> mapaErro = new HashMap<>();
        mapaErro.put("erro", e.getMessage());

        retornarRespostaJson(resp, mapaErro, HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
    }

    private static String bodyRequestToString(HttpServletRequest req) throws IOException {
        req.setCharacterEncoding("UTF-8");
        StringBuilder string = new StringBuilder();

        req.getReader().lines().forEach(
            s -> string.append(s)
        );
        
        return string.toString();
    }

}
