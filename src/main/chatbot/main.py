from fastapi import FastAPI
from fastapi.middleware.cors import CORSMiddleware

from app.mensagem import Mensagem
from app.chatbot import gerarResposta
from app.chatbot import modelo

app = FastAPI()

app.add_middleware(
    CORSMiddleware,
    allow_origins=["http://localhost:8080"],
    allow_credentials=True,
    allow_methods=["*"],
    allow_headers=["*"],
)

sessoes = {}

@app.post("/get-message")
async def message(mensagem: Mensagem):
    idcliente = mensagem.idcliente

    if idcliente not in sessoes:
        sessoes[idcliente] = modelo.start_chat()

    chat = sessoes[idcliente]

    response = gerarResposta(chatbot=chat, mensagem=mensagem, idcliente=idcliente)

    return { "pergunta" : mensagem.mensagem, "resposta" : response.text }