package com.esboco_comix.controller.impl;

import java.io.IOException;

import com.esboco_comix.controller.utils.AbstractController;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class SpaController extends AbstractController {

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
    String path = req.getRequestURI();

    if (path.startsWith("/api/") || path.contains(".")) {
      return;
    }

    req.getRequestDispatcher("/index.html")
       .forward(req, resp);
  }
}
