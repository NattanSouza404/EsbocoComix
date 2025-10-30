package com.esboco_comix;

import com.esboco_comix.config.ServidorConfig;
import org.apache.catalina.startup.Tomcat;

public class Main {

	public static void main(String[] args) throws Exception {
		Tomcat tomcat = new Tomcat();

		ServidorConfig.configurar(tomcat);

		tomcat.start();
		
		System.out.println("Servidor rodando em http://localhost:8080");
		tomcat.getServer().await();
	}
	
}