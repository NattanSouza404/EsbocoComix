CREATE OR REPLACE PROCEDURE inserir_entrada_estoque(
    ees_qua_id      INTEGER,
    ees_quantidade  INTEGER,
    ees_valor_custo DOUBLE PRECISION,
    ees_fornecedor  VARCHAR,
	OUT new_ees_id NUMERIC(6)  
)
LANGUAGE plpgsql
AS $$
BEGIN
    -- 1) Tenta inserir no estoque; se já existir o item (pelo campo est_qua_id), não faz nada
    INSERT INTO estoque (est_qua_id, est_quantidade_total)
    VALUES (ees_qua_id, ees_quantidade)
    ON CONFLICT (est_qua_id)
		DO UPDATE
  		SET est_quantidade_total =
		  estoque.est_quantidade_total + EXCLUDED.est_quantidade_total;

    -- 2) Registra a entrada no histórico
    INSERT INTO entrada_estoque (
        ees_qua_id,
        ees_quantidade,
        ees_valor_custo,
        ees_dt_entrada,
        ees_fornecedor
    ) VALUES (
        ees_qua_id,
        ees_quantidade,
        ees_valor_custo,
        CURRENT_TIMESTAMP,
        ees_fornecedor
    )
	RETURNING ees_id INTO new_ees_id;
END;
$$;