from pydantic import BaseModel

class Mensagem(BaseModel):
    idcliente: int
    mensagem: str