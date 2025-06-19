package com.esboco_comix;

import com.esboco_comix.config.ServidorConfig;
import org.apache.catalina.startup.Tomcat;

public class Main {

	public static void main(String[] args) throws Exception {
		Tomcat tomcat = new Tomcat();

		ServidorConfig.configurar(tomcat);

		tomcat.start();
		tomcat.getServer().await();
	}
	
}