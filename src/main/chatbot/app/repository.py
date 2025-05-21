from app.database_config import get_conexao

def retornarTodosQuadrinhos():
    conn = get_conexao()
    cur = conn.cursor()
    cur.execute("SELECT qua_titulo, qua_autor FROM quadrinhos")
    quadrinhos = cur.fetchall()
    cur.close()
    conn.close()
    return quadrinhos