package com.esboco_comix.core.config;

import java.io.File;
import java.util.Map;

import com.esboco_comix.analise.controller.AnaliseController;
import com.esboco_comix.carrinho.controller.CarrinhoController;
import com.esboco_comix.cliente.controller.CartaoCreditoController;
import com.esboco_comix.cliente.controller.ClienteController;
import com.esboco_comix.cliente.controller.EnderecoController;
import com.esboco_comix.core.controller.impl.*;
import com.esboco_comix.core.controller.utils.SpaFilter;
import com.esboco_comix.cupom.controller.CupomController;
import com.esboco_comix.estoque.controller.EstoqueController;
import com.esboco_comix.integracao.controller.ChatbotProxyController;
import com.esboco_comix.pedido.controller.PedidoController;
import com.esboco_comix.pedido.controller.PedidoPosVendaController;
import com.esboco_comix.quadrinho.controller.QuadrinhoController;

import jakarta.servlet.http.HttpServlet;
import org.apache.catalina.Context;
import org.apache.catalina.startup.Tomcat;
import org.apache.tomcat.util.descriptor.web.FilterDef;
import org.apache.tomcat.util.descriptor.web.FilterMap;

public class ServidorConfig {

	public static void configurar(Tomcat tomcat) {
		tomcat.setPort(8080);
		tomcat.getConnector();

		String webappDir;
		File idePath = new File("src/main/webapp");
		File dockerPath = new File("/app/webapp");

		if (idePath.exists()) {
			webappDir = idePath.getAbsolutePath();
		} else if (dockerPath.exists()) {
			webappDir = dockerPath.getAbsolutePath();
		} else {
			throw new RuntimeException("Diretório do webapp não encontrado!");
		}

		Context context = tomcat.addWebapp("", webappDir);
		configurarRotas(context);
    }

	public static void configurarRotas(Context context) {
		Map<String, HttpServlet> servlets = Map.ofEntries(
				Map.entry("api/cliente", new ClienteController()),
				Map.entry("api/endereco", new EnderecoController()),
				Map.entry("api/cartaocredito", new CartaoCreditoController()),
				Map.entry("api/quadrinho", new QuadrinhoController()),
				Map.entry("api/pedido", new PedidoController()),
				Map.entry("api/carrinho", new CarrinhoController()),
				Map.entry("api/cupom", new CupomController()),
				Map.entry("api/estoque", new EstoqueController()),
				Map.entry("api/analise", new AnaliseController()),
				Map.entry("api/pedido_pos_venda", new PedidoPosVendaController()),
				Map.entry("api/chatbot", new ChatbotProxyController())
		);

		for (Map.Entry<String, HttpServlet> entry: servlets.entrySet()){
			String path = "/" + entry.getKey() + "/*";
			Tomcat.addServlet(context, entry.getKey(), entry.getValue());
			context.addServletMappingDecoded(path, entry.getKey());
		}

		Tomcat.addServlet(context, "spa", new SpaController());
		context.addServletMappingDecoded("/", "spa");

		FilterDef filterDef = new FilterDef();
		filterDef.setFilterName("spaFilter");
		filterDef.setFilterClass(SpaFilter.class.getName());
		filterDef.setFilter(new SpaFilter());
		context.addFilterDef(filterDef);

		FilterMap filterMap = new FilterMap();
		filterMap.setFilterName("spaFilter");
		filterMap.addURLPattern("/*");
		context.addFilterMap(filterMap);
	}
}
