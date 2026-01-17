package com.esboco_comix.controller.utils;

import jakarta.servlet.http.HttpServletRequest;

@FunctionalInterface
public interface RouteHandler {
    Object handle(HttpServletRequest request) throws Exception;
}
