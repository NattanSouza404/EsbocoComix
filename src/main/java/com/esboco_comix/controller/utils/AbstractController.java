package com.esboco_comix.controller.utils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.esboco_comix.utils.ConversorJson;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public abstract class AbstractController extends HttpServlet {
    
    public HttpSession getSession(HttpServletRequest req) {
        return req.getSession();
    }

    public void retornarRespostaJson(HttpServletResponse resp, Object object, int sc) throws IOException {
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json; charset=UTF-8");
        resp.getWriter().println(
            ConversorJson.toJson(object)
        );
        resp.setStatus(sc);
    }

    public <T> T jsonToObject(HttpServletRequest req, Class<T> classe) throws Exception {
        return ConversorJson.jsonToObject(
            bodyRequestToString(req), classe
        );
    }

    public void estourarErro(HttpServletResponse resp, Exception e) throws IOException {
        System.err.println(e.getMessage());
        e.printStackTrace();

        Map<Object, Object> mapaErro = new HashMap<>();

        if (e.getCause() != null){
            mapaErro.put("erro", e.getMessage()+": "+ e.getCause().getMessage());
        } else {
            mapaErro.put("erro", e.getMessage()+"!");
        }

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
