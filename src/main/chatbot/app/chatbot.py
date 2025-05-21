import google.generativeai as genai
from app.config import api_key
from app.repository import retornarTodosQuadrinhos
from app.mensagem import Mensagem

genai.configure(api_key=api_key)

chatbot = genai.GenerativeModel('gemini-1.5-flash')

def gerarResposta(mensagem: Mensagem):
    quadrinhos = ""

    for qua_titulo, qua_autor in retornarTodosQuadrinhos():
        quadrinhos += f"Quadrinho: Titulo({qua_titulo}) Autor ({qua_autor})\n\n"

    prompt = f"""
        Você é um assistente virtual de uma plataforma de Ecommerce de quadrinhos, 
        seu papel é ajudar o usuário em relação aos produtos.
        Os únicos quadrinhos que você pode falar sobre são esses: {quadrinhos}.

        Agora você deve responder a essa pergunta do usuário: {mensagem.mensagem}
    """

    return chatbot.generate_content(prompt)