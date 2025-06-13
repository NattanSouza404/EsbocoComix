import psycopg2

from app.config.config import db_host, db_port, db_database, db_user, db_password

def get_conexao():
    return psycopg2.connect(
        host =      db_host,
        port =      db_port,
        database =  db_database,
        user =      db_user,
        password =  db_password
    )