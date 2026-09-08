package com.esboco_comix.core.routing;

import java.util.Map;
import java.util.NoSuchElementException;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Router {
    private final Map<String, RouteHandler> rotas;

    public RouteHandler getHandler(String path){
        var handler = rotas.get(path);
        
        if (handler == null) {
            throw new NoSuchElementException("Endpoint não encontrado");
        }

        return handler;
    }

    @FunctionalInterface
    public interface RouteHandler {
        Object handle(HttpServletRequest request) throws Exception;
    }
}
