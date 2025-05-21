from fastapi import FastAPI
from fastapi.middleware.cors import CORSMiddleware

from app.mensagem import Mensagem
from app.chatbot import gerarResposta

app = FastAPI()

app.add_middleware(
    CORSMiddleware,
    allow_origins=["http://localhost:8080"],
    allow_credentials=True,
    allow_methods=["*"],
    allow_headers=["*"],
)

@app.post("/get-message")
async def message(mensagem: Mensagem):
    response = gerarResposta(mensagem=mensagem)

    return { "pergunta" : mensagem.mensagem, "resposta" : response.text, "status": 201 }