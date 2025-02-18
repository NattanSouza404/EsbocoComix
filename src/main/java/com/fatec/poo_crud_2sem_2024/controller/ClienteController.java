package com.fatec.poo_crud_2sem_2024.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fatec.poo_crud_2sem_2024.controller.utils.ServletUtil;
import com.fatec.poo_crud_2sem_2024.dao.impl.ClienteDAO;

public class ClienteController extends HttpServlet {
    private static ClienteDAO clienteDAO = new ClienteDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {

            String parametroId = req.getParameter("id"); 
            
            if (parametroId == null){
                ServletUtil.retornarJson(resp, clienteDAO.consultarTodos());
                return;
            }

            int id = Integer.parseInt(parametroId);

            ServletUtil.retornarJson(
                resp,
                clienteDAO.consultarByID(id)
            );

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

}
