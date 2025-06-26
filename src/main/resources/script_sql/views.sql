CREATE VIEW vw_quadrinhos AS
	SELECT
	    quadrinhos.*,
	    est_quantidade_total,
	    grupos_precificacao.*,
	    categorias.*,
	    MIN(ees_valor_custo) * (1 + (gpr_porcentagem)/100) as preco
	FROM
	    quadrinhos
	    JOIN categorias_quadrinho ON qua_id = cqu_qua_id
	    JOIN categorias ON cat_id = cqu_cat_id
	    JOIN grupos_precificacao ON qua_gpr_id = gpr_id
	    LEFT JOIN estoque ON est_qua_id = qua_id
	    LEFT JOIN entrada_estoque ON ees_qua_id = qua_id
	GROUP BY
		qua_id, qua_autor, qua_ano, qua_titulo, qua_editora, qua_edicao,
		qua_isbn, qua_numero_paginas, qua_sinopse, qua_altura_cm,
		qua_largura_cm, qua_profundidade, qua_peso_gramas,
		qua_codigo_barras, qua_is_ativo, qua_gpr_id,
		qua_url_imagem, est_quantidade_total, gpr_id, gpr_nome,
		gpr_porcentagem, cat_id, cat_nome
	ORDER BY qua_id DESC;

CREATE VIEW vw_analise_produtos AS
	SELECT
		qua_titulo AS titulo_quadrinho,
		SUM(ite_quantidade) AS quantidade,
		ite_valor_unitario AS valor_unitario,
		SUM(ite_valor_unitario * ite_quantidade) AS valor_total,
		DATE(ped_data) AS data
	FROM itens_pedido
		JOIN quadrinhos ON ite_qua_id = qua_id
		JOIN pedidos ON ite_ped_id = ped_id
	GROUP BY
		titulo_quadrinho, data, valor_unitario
	ORDER BY
		data;

CREATE VIEW vw_analise_categorias AS
	SELECT
		cat_nome AS categoria,
		SUM(ite_quantidade) AS quantidade,
		ite_valor_unitario AS valor_unitario,
		SUM(ite_valor_unitario * ite_quantidade) AS valor_total,
		DATE(ped_data) AS data
	FROM categorias
	    JOIN categorias_quadrinho ON cat_id = cqu_cat_id
	    JOIN quadrinhos ON qua_id = cqu_qua_id
	    JOIN itens_pedido ON ite_qua_id = qua_id
	    JOIN pedidos ON ite_ped_id = ped_id
	GROUP BY
		categoria, data, valor_unitario
    ORDER BY
		data;