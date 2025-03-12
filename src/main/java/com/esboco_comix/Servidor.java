package com.esboco_comix;

import java.io.File;

import org.apache.catalina.Context;
import org.apache.catalina.startup.Tomcat;

import com.esboco_comix.controller.CartaoCreditoController;
import com.esboco_comix.controller.ClienteController;
import com.esboco_comix.controller.EnderecoController;
import com.esboco_comix.controller.IndexController;

public class Servidor {

    public static void iniciar() throws Exception {
        Tomcat tomcat = new Tomcat();
		tomcat.setPort(8080);

		Context context = tomcat.addWebapp(
			"",
			new File("src/main/webapp").getAbsolutePath()
		);

		Tomcat.addServlet(context, "index", new IndexController());
		context.addServletMapping("", "index");

		Tomcat.addServlet(context, "apicliente", new ClienteController());
		context.addServletMapping("/cliente", "apicliente");

		Tomcat.addServlet(context, "apiendereco", new EnderecoController());
		context.addServletMapping("/endereco", "apiendereco");

		Tomcat.addServlet(context, "apicartaocredito", new CartaoCreditoController());
		context.addServletMapping("/cartaocredito", "apicartaocredito");

		tomcat.start();
		tomcat.getServer().await();
    }

}
