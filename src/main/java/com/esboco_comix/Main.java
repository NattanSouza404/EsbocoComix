package com.esboco_comix;

import com.esboco_comix.config.ServidorConfig;
import org.apache.catalina.Context;
import org.apache.catalina.startup.Tomcat;

import java.io.File;

public class Main {

	public static void main(String[] args) throws Exception {
		Tomcat tomcat = new Tomcat();
		tomcat.setPort(8080);

		tomcat.getConnector();

		Context context = tomcat.addWebapp(
			"",
			new File("src/main/webapp").getAbsolutePath()
		);

		ServidorConfig.configurarRotas(context);

		tomcat.start();
		tomcat.getServer().await();
	}

	/* PRA ESSA ENTREGA */
	// IA inteira
	// TODO : fazer o subsistema Pythônico (ver alura)
	// TODO : refazer o chat lá pra ficar mais lindo
	// FIXME : inserts de categorias, grupos de precificação e etc

	// Requisitos a cumprir
	// TODO : produto não presente no estoque, num pode adicionar ao carrinho
	// TODO : não permitir a compra do produto se ele não estiver disponível
	// TODO : lógica nos pedidos, principalmente referente à atualização de status
	// TODO : permitir a troca de uma quantidade específica de itens (se eu comprei 3 mouses, posso pedir pra devolver
	//  apenas um) criar tabela de pedidos de troca e devolucao
	// TODO : negócio lá do DEFAULT TIMESTAMP na tabela/procedure de entrada_estoque
	// TODO : aquela regra de gerar cupom de troca ao usar cupons de valor que excedem a compra, sla oq
	// TODO : adicionar novo cartão e novo endereço na compra

	/* REFAZIMENTOS */
	// TODO : refatorar os códigos testes da conta
	// TODO : fazer as validações corretas das entidades ao inserir e etc (nulo, número, isBlank)
	// TODO : revisar as exceções nos daos (tudo bem não haver nenhum registro)
	// TODO : ver como deixar mais lindio
	// TODO : pensar na lógica dos DTOs

	// TODO : atualizar corretamente o cupom na tela de compras

	/* Detalhes */
	// TODO : bug do consultar transações (Não pode clicar duas vezes rápido)
	// TODO : criar mascaras no html
	// TODO : código de cupom?? código de pedido
	// TODO : titulos dos htmls, ids em inglês tbdoy ordertable

	/* SÓ IDEIAS */
	// TODO : docker
	// TODO : consulta páginada dos cliente
	// TODO : retrabalho nas chamadas de API do front para lidar certo com exceções

	// TODO : fazer tela reativa/forçar F5

	// TODO : contexto GEminão?? (contexto do egommerce)
	// TODO : completar o filtro dos quadrinhos

}