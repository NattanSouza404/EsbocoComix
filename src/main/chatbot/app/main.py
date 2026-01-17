from fastapi import FastAPI
from fastapi.middleware.cors import CORSMiddleware

from app.model.mensagem import Mensagem
from app.chatbot.chatbot import iniciarChat, gerarResposta

from app.config.config import BACKEND_URL

app = FastAPI()

app.add_middleware(
    CORSMiddleware,
    allow_origins=[BACKEND_URL],
    allow_credentials=True,
    allow_methods=["*"],
    allow_headers=["*"],
)

sessoes = {}

@app.post("/api/chatbot/get-message")
async def message(mensagem: Mensagem):
    idcliente = mensagem.idcliente

    if idcliente not in sessoes:
        sessoes[idcliente] = iniciarChat()

    chat = sessoes[idcliente]

    response = gerarResposta(chatbot=chat, mensagem=mensagem)

    return { "pergunta" : mensagem.mensagem, "resposta" : response.text }