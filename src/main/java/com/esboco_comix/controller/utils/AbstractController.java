package com.esboco_comix.controller.utils;

import java.io.IOException;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public abstract class AbstractController extends HttpServlet {
    
    public HttpSession getSession(HttpServletRequest req) {
        return req.getSession();
    }

    public void retornarRespostaJson(HttpServletResponse resp, Object object, int sc) throws IOException {
        ServletUtil.retornarRespostaJson(resp, object, sc);
    }

    public <T> T jsonToObject(HttpServletRequest req, Class<T> classe) throws Exception {
        return ServletUtil.jsonToObject(req, classe);
    }

    public void estourarErro(HttpServletResponse resp, Exception e) throws IOException {
        ServletUtil.estourarErro(resp, e);
    }
    
}
