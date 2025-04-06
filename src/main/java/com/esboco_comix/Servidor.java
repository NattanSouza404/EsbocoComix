package com.esboco_comix;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.Context;
import org.apache.catalina.startup.Tomcat;

import com.esboco_comix.controller.CartaoCreditoController;
import com.esboco_comix.controller.ClienteController;
import com.esboco_comix.controller.EnderecoController;
import com.esboco_comix.controller.QuadrinhoController;
import com.esboco_comix.controller.PedidoController;

import lombok.Getter;

public class Servidor {

	public static void iniciar() throws Exception {
		Tomcat tomcat = new Tomcat();
		tomcat.setPort(8080);

		Context context = tomcat.addWebapp(
				"",
				new File("src/main/webapp").getAbsolutePath());

		configurarRotas(context);

		tomcat.start();
		tomcat.getServer().await();
	}

	private static void configurarRotas(Context context) {

		List<ForwadingServlet> servlets = Arrays.asList(
			new ForwadingServlet("login", "/paginas/login/login.html"),

			new ForwadingServlet("anuncio", "/paginas/cliente/anuncio/anuncio.html"),
			new ForwadingServlet("cadastrar", "/paginas/cliente/cadastrar/cadastrar.html"),
			new ForwadingServlet("carrinho", "/paginas/cliente/carrinho/carrinho.html"),
			new ForwadingServlet("compra", "/paginas/cliente/compra/compra.html"),
			new ForwadingServlet("conta", "/paginas/cliente/conta/conta.html"),
			new ForwadingServlet("index", "/paginas/cliente/index/index.html"),
			new ForwadingServlet("minhasCompras", "/paginas/cliente/minhasCompras/minhasCompras.html"),

			new ForwadingServlet("admin/analise", "/paginas/admin/analise/analise.html"),
			new ForwadingServlet("admin/clientes", "/paginas/admin/clientes/clientes.html"),
			new ForwadingServlet("admin/estoque", "/paginas/admin/estoque/estoque.html"),
			new ForwadingServlet("admin/gerenciarVendas", "/paginas/admin/gerenciarVendas/gerenciarVendas.html")
		);

		for (ForwadingServlet servlet : servlets) {
			Tomcat.addServlet(context, servlet.getNome(), servlet);
			context.addServletMapping("/"+servlet.getNome(), servlet.getNome());
		}
		context.addServletMapping("/index.html", "index");

		Tomcat.addServlet(context, "apicliente", new ClienteController());
		context.addServletMapping("/cliente", "apicliente");

		Tomcat.addServlet(context, "apiendereco", new EnderecoController());
		context.addServletMapping("/endereco", "apiendereco");

		Tomcat.addServlet(context, "apicartaocredito", new CartaoCreditoController());
		context.addServletMapping("/cartaocredito", "apicartaocredito");

		Tomcat.addServlet(context, "apiquadrinho", new QuadrinhoController());
		context.addServletMapping("/quadrinho", "apiquadrinho");

		Tomcat.addServlet(context, "apipedido", new PedidoController());
		context.addServletMapping("/pedido", "apipedido");
	}

	@Getter
	private static class ForwadingServlet extends HttpServlet {
		private String nome;
		private String path;

		private ForwadingServlet(String nome, String path) {
			this.nome = nome;
			this.path = path;
		}

		@Override
		protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			req.getRequestDispatcher(path).forward(req, resp);
		}

	}

}
