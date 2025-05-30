package com.esboco_comix.controller.utils;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Getter;

import java.io.IOException;

@Getter
public class ForwardingServlet extends AbstractController {
    private final String path;

    public ForwardingServlet(String path) {
        this.path = path;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(path).forward(req, resp);
    }

}
