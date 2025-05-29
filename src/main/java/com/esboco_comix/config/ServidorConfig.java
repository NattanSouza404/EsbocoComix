package com.esboco_comix.config;

import java.util.Map;

import com.esboco_comix.controller.impl.*;
import com.esboco_comix.controller.utils.ForwardingServlet;
import jakarta.servlet.http.HttpServlet;
import org.apache.catalina.Context;
import org.apache.catalina.startup.Tomcat;

public class ServidorConfig {

	public static void configurarRotas(Context context) {

		context.addWelcomeFile("paginas/cliente/index/index.html");
		context.addErrorPage(new PaginaErro404());

		Map<String, HttpServlet> servlets = Map.ofEntries(
				Map.entry("login", new ForwardingServlet("/paginas/login/login.html")),

				Map.entry("index", new ForwardingServlet("/paginas/cliente/index/index.html")),

				Map.entry("anuncio", new ForwardingServlet("/paginas/cliente/anuncio/anuncio.html")),
				Map.entry("cadastrar", new ForwardingServlet("/paginas/cliente/cadastrar/cadastrar.html")),
				Map.entry("carrinho", new ForwardingServlet("/paginas/cliente/carrinho/carrinho.html")),
				Map.entry("compra", new ForwardingServlet("/paginas/cliente/compra/compra.html")),
				Map.entry("conta", new ForwardingServlet("/paginas/cliente/conta/conta.html")),
				Map.entry("minhasCompras", new ForwardingServlet("/paginas/cliente/minhasCompras/minhasCompras.html")),

				Map.entry("admin/analise", new ForwardingServlet("/paginas/admin/analise/analise.html")),
				Map.entry("admin/clientes", new ForwardingServlet("/paginas/admin/clientes/clientes.html")),
				Map.entry("admin/estoque", new ForwardingServlet("/paginas/admin/estoque/estoque.html")),
				Map.entry("admin/gerenciarVendas", new ForwardingServlet("/paginas/admin/gerenciarVendas/gerenciarVendas.html")),

				Map.entry("api/cliente", new ClienteController()),
				Map.entry("api/endereco", new EnderecoController()),
				Map.entry("api/cartaocredito", new CartaoCreditoController()),
				Map.entry("api/quadrinho", new QuadrinhoController()),
				Map.entry("api/pedido", new PedidoController()),
				Map.entry("api/carrinho", new CarrinhoController()),
				Map.entry("api/cupom", new CupomController()),
				Map.entry("api/estoque", new EstoqueController()),
				Map.entry("api/analise", new AnaliseController())
		);

		for (Map.Entry<String, HttpServlet> entry: servlets.entrySet()){
			String path = "/" + entry.getKey();
			Tomcat.addServlet(context, entry.getKey(), entry.getValue());
			context.addServletMappingDecoded(path, entry.getKey());
		}

	}

}
