package com.fatec;

import java.io.File;

import org.apache.catalina.Context;
import org.apache.catalina.startup.Tomcat;

import com.fatec.controller.ClienteController;
import com.fatec.controller.IndexController;

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
