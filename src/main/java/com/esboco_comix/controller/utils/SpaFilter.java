package com.esboco_comix.controller.utils;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class SpaFilter implements Filter {

  @Override
  public void doFilter(
      ServletRequest request,
      ServletResponse response,
      FilterChain chain
  ) throws IOException, ServletException {

    HttpServletRequest req = (HttpServletRequest) request;
    HttpServletResponse resp = (HttpServletResponse) response;

    String path = req.getRequestURI();

    // deixa API passar
    if (path.startsWith("/api/") || path.contains(".")) {
      chain.doFilter(request, response);
      return;
    }

    // fallback SPA
    req.getRequestDispatcher("/index.html")
       .forward(req, resp);
  }
}
