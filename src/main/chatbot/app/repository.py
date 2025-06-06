from app.database_config import get_conexao

def consultarClienteByID(idcliente):
    conn = get_conexao()
    cur = conn.cursor()
    cur.execute(f"SELECT cli_nome, cli_genero, cli_dt_nascimento FROM clientes where cli_id = {idcliente}")
    cliente = cur.fetchall()
    
    close(cur, conn)

    return cliente

def consultarQuadrinhos():
    conn = get_conexao()
    cur = conn.cursor()
    sql = """
        SELECT
            qua_id,
            qua_titulo,
            qua_autor,
            qua_sinopse,
            cat_id,
            cat_nome,
            gpr_nome,
            gpr_porcentagem
        FROM quadrinhos q
        LEFT JOIN categorias_quadrinho cq ON q.qua_id = cq.cqu_qua_id
        LEFT JOIN categorias c             ON cq.cqu_cat_id = c.cat_id
        JOIN grupos_precificacao ON q.qua_gpr_id = gpr_id
        ORDER BY q.qua_id;
    """
    cur.execute(sql)

    resultados = cur.fetchall()

    mapa = {}
    for qua_id, qua_titulo, qua_autor, qua_sinopse, cat_id, cat_nome, gpr_nome, gpr_porcentagem in resultados:
        if qua_id not in mapa:
            mapa[qua_id] = {
                "titulo": qua_titulo,
                "autor": qua_autor,
                "sinopse": qua_sinopse,
                "grupoPrecificao": gpr_nome,
                "grupoPrecificaoMargemLucro": gpr_porcentagem,
                "categorias": []
            }

        if cat_id is not None:
            mapa[qua_id]["categorias"].append(cat_nome)

    close(cur, conn)

    return mapa

def consultarPedidosByIDCliente(idcliente):
    conn = get_conexao()
    cur = conn.cursor()

    sql = f"""
    SELECT
        ped_id,
        ped_data,
        ped_status,
        ite_quantidade,
        ite_status,
        qua_id,
        qua_titulo
    FROM pedidos
        JOIN itens_pedido ON ped_id = ite_ped_id
        JOIN quadrinhos ON qua_id = ite_qua_id
        JOIN clientes ON cli_id = ped_cli_id
    WHERE cli_id = {idcliente};
    """

    cur.execute(sql)

    resultados = cur.fetchall()

    mapa = {}
    for ped_id, ped_data, ped_status, ite_quantidade, ite_status, qua_id, qua_titulo in resultados:
        if ped_id not in mapa:
            mapa[ped_id] = {
                "idPedido": ped_id,
                "dataPedido": ped_data,
                "status": ped_status,
                "itensPedido": []
            }

        if ped_id is not None:
            mapa[ped_id]["itensPedido"].append(
                {
                    "idQuadrinho": qua_id,
                    "quantidade": ite_quantidade,
                    "titulo": qua_titulo,
                    "status": ite_status
                }
            )

    close(cur, conn)

    return mapa

def close(cur, conn):
    cur.close()
    conn.close()