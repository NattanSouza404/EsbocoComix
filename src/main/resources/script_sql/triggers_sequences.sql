-- Clientes
CREATE SEQUENCE cli_sq
    START WITH 1
    INCREMENT BY 1
    MINVALUE 1
    NO MAXVALUE
    CACHE 1;

CREATE FUNCTION set_cli_id() RETURNS trigger
    LANGUAGE plpgsql
    AS $$
BEGIN
    NEW.cli_id := nextval('cli_sq');
    RETURN NEW;
END;
$$;

CREATE TRIGGER tg_set_cli_id
BEFORE INSERT ON clientes
FOR EACH ROW
EXECUTE FUNCTION set_cli_id();





-- Grupo de precificação
CREATE SEQUENCE gpr_sq
    START WITH 1
    INCREMENT BY 1
    MINVALUE 1
    NO MAXVALUE
    CACHE 1;

CREATE FUNCTION set_gpr_id() RETURNS trigger
    LANGUAGE plpgsql
    AS $$
BEGIN
    NEW.gpr_id := nextval('gpr_sq');
    RETURN NEW;
END;
$$;

CREATE TRIGGER tg_set_gpr_id
BEFORE INSERT ON grupos_precificacao
FOR EACH ROW
EXECUTE FUNCTION set_gpr_id();





-- Endereços
CREATE SEQUENCE end_sq
    START WITH 1
    INCREMENT BY 1
    MINVALUE 1
    NO MAXVALUE
    CACHE 1;

CREATE FUNCTION set_end_id() RETURNS trigger
    LANGUAGE plpgsql
    AS $$
BEGIN
    NEW.end_id := nextval('end_sq');
    RETURN NEW;
END;
$$;

CREATE TRIGGER tg_set_end_id
BEFORE INSERT ON enderecos
FOR EACH ROW
EXECUTE FUNCTION set_end_id();





-- Cartao de crédito
CREATE SEQUENCE cre_sq
    START WITH 1
    INCREMENT BY 1
    MINVALUE 1
    NO MAXVALUE
    CACHE 1;

CREATE FUNCTION set_cre_id() RETURNS trigger
    LANGUAGE plpgsql
    AS $$
BEGIN
    NEW.cre_id := nextval('cre_sq');
    RETURN NEW;
END;
$$;

CREATE TRIGGER tg_set_cre_id
BEFORE INSERT ON cartoes_credito
FOR EACH ROW
EXECUTE FUNCTION set_cre_id();





-- Bandeiras de cartão de crédito

CREATE SEQUENCE bcc_sq
    START WITH 1
    INCREMENT BY 1
    MINVALUE 1
    NO MAXVALUE
    CACHE 1;

CREATE FUNCTION set_bcc_id() RETURNS trigger
    LANGUAGE plpgsql
    AS $$
BEGIN
    NEW.bcc_id := nextval('bcc_sq');
    RETURN NEW;
END;
$$;

CREATE TRIGGER tg_set_bcc_id
BEFORE INSERT ON bandeiras_cartao_credito
FOR EACH ROW
EXECUTE FUNCTION set_bcc_id();





-- Quadrinhos
CREATE SEQUENCE qua_sq
    START WITH 1
    INCREMENT BY 1
    MINVALUE 1
    NO MAXVALUE
    CACHE 1;

CREATE FUNCTION set_qua_id() RETURNS trigger
    LANGUAGE plpgsql
    AS $$
BEGIN
    NEW.qua_id := nextval('qua_sq');
    RETURN NEW;
END;
$$;

CREATE TRIGGER tg_set_qua_id
BEFORE INSERT ON quadrinhos
FOR EACH ROW
EXECUTE FUNCTION set_qua_id();




-- Entrada estoque

CREATE SEQUENCE ees_sq
    START WITH 1
    INCREMENT BY 1
    MINVALUE 1
    NO MAXVALUE
    CACHE 1;

CREATE FUNCTION set_ees_id() RETURNS trigger
    LANGUAGE plpgsql
    AS $$
BEGIN
    NEW.ees_id := nextval('ees_sq');
    RETURN NEW;
END;
$$;

CREATE TRIGGER tg_set_ees_id
BEFORE INSERT ON entrada_estoque
FOR EACH ROW
EXECUTE FUNCTION set_ees_id();




-- Pedidos
CREATE SEQUENCE ped_sq
    START WITH 1
    INCREMENT BY 1
    MINVALUE 1
    NO MAXVALUE
    CACHE 1;

CREATE FUNCTION set_ped_id() RETURNS trigger
    LANGUAGE plpgsql
    AS $$
BEGIN
    NEW.ped_id := nextval('ped_sq');
    RETURN NEW;
END;
$$;

CREATE TRIGGER tg_set_ped_id
BEFORE INSERT ON pedidos
FOR EACH ROW
EXECUTE FUNCTION set_ped_id();





-- Cupons

CREATE SEQUENCE cup_sq
    START WITH 1
    INCREMENT BY 1
    MINVALUE 1
    NO MAXVALUE
    CACHE 1;

CREATE FUNCTION set_cup_id() RETURNS trigger
    LANGUAGE plpgsql
    AS $$
BEGIN
    NEW.cup_id := nextval('cup_sq');
    RETURN NEW;
END;
$$;

CREATE TRIGGER tg_set_cup_id
BEFORE INSERT ON cupons
FOR EACH ROW
EXECUTE FUNCTION set_cup_id();
