package com.esboco_comix;

import com.esboco_comix.config.ServidorConfig;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.apache.catalina.connector.Connector;
import org.apache.catalina.startup.Tomcat;

public class Main {

	public static void main(String[] args) throws Exception {
		Tomcat tomcat = new Tomcat();

		ServidorConfig.configurar(tomcat);

		tomcat.start();

		logarEnderecoServidor(tomcat);

		tomcat.getServer().await();
	}

	public static void logarEnderecoServidor(Tomcat tomcat) throws UnknownHostException {
		Connector connector = tomcat.getConnector();
		String host = InetAddress.getLocalHost().getHostAddress();
		int port = connector.getPort();

		System.out.printf("Servidor rodando em http://%s:%d%n", host, port);
	}
	
}