package com.fatec.poo_crud_2sem_2024.controller.utils;

import java.io.IOException;
import java.util.stream.Stream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ServletUtil {

    public static void retornarJson(HttpServletResponse resp, Object object) throws IOException {
        resp.getWriter().println(
            ConversorJson.toJson(object)
        );
    }

    public static String bodyRequestToString(HttpServletRequest req) throws IOException {
        Stream<String> string = req.getReader().lines();
        StringBuilder stringRetorno = new StringBuilder();
        string.forEach(e -> stringRetorno.append(e));
        return stringRetorno.toString();
    }

}
