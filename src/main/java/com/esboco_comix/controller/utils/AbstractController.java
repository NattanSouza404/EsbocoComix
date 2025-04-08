package com.esboco_comix.controller.utils;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.esboco_comix.model.Carrinho;

public abstract class AbstractController extends HttpServlet {
    
    public HttpSession getSession(HttpServletRequest req) {
        return SessionManager.getSession(req);
    }

    public Carrinho retornarCarrinhoSessao(HttpServletRequest req){
        return SessionManager.retornarCarrinhoSessao(req);  
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
