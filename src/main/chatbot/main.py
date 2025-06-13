from fastapi import FastAPI
from fastapi.middleware.cors import CORSMiddleware

from app.model.mensagem import Mensagem
from app.chatbot.chatbot import iniciarChat, gerarResposta

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
        sessoes[idcliente] = iniciarChat()

    chat = sessoes[idcliente]

    response = gerarResposta(chatbot=chat, mensagem=mensagem)

    return { "pergunta" : mensagem.mensagem, "resposta" : response.text }