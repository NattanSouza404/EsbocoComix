package com.esboco_comix.controller.utils;

import java.util.Map;
import java.util.NoSuchElementException;

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
}
