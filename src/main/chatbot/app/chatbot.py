import google.generativeai as genai
from app.config import api_key
from app.repository import mapearCategoriasQuadrinhos, consultarClienteByID, consultarPedidosByIDCliente
from app.mensagem import Mensagem

genai.configure(api_key=api_key)

modelo = genai.GenerativeModel('gemini-1.5-flash')

def gerarResposta(chatbot: any, mensagem: Mensagem, idcliente):
    quadrinhos = mapearCategoriasQuadrinhos()

    usuario = consultarClienteByID(mensagem.idcliente)
    pedidos = consultarPedidosByIDCliente(idcliente)

    prompt = f"""
        Você é um assistente virtual de uma plataforma de Ecommerce de quadrinhos, 
        seu papel é ajudar o usuário em relação aos produtos.
        
        Os únicos quadrinhos que você pode falar sobre são esses: {quadrinhos}.
        O usuário é este aqui: {usuario}
        Os quadrinhos que ele já comprou são: {pedidos}

        Agora você deve responder a essa pergunta do usuário: {mensagem.mensagem}
    """

    return chatbot.send_message(prompt)