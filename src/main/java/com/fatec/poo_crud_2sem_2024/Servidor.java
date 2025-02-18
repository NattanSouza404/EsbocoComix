package com.fatec.poo_crud_2sem_2024;

import java.io.File;

import org.apache.catalina.Context;
import org.apache.catalina.startup.Tomcat;

import com.fatec.poo_crud_2sem_2024.controller.ClienteController;
import com.fatec.poo_crud_2sem_2024.controller.IndexController;

public class Servidor {

    public static void iniciar() throws Exception {
        Tomcat tomcat = new Tomcat();
		tomcat.setPort(8080);

		Context context = tomcat.addWebapp(
			"",
			new File("src/main/resources/static").getAbsolutePath()
		);

		Tomcat.addServlet(context, "index", new IndexController());
		context.addServletMapping("", "index");

		Tomcat.addServlet(context, "apicliente", new ClienteController());
		context.addServletMapping("/cliente", "apicliente");

		tomcat.start();
		tomcat.getServer().await();
    }

}
