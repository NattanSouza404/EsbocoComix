import google.generativeai as genai
from app.config.config import API_KEY, GEMINI_MODEL
from app.model.mensagem import Mensagem
from app.api_call.api_call import consultarQuadrinhos, consultarClienteByID, consultarPedidosByIDCliente

genai.configure(api_key=API_KEY)

modelo = genai.GenerativeModel(GEMINI_MODEL)

def iniciarChat():
    return modelo.start_chat(
        history=[
            {"role": "user", "parts": [
                """
                Você é um assistente de e-commerce de quadrinhos.
                Você pode redirecionar o usuário para os anúncios dos quadrinhos, 
                mandando-os para um link como "[nomeQuadrinho](/anuncio?id=id)" onde você substitui o id para o id do quadrinho e nomeQuadrinho para o titulo do quadrinho.
                Se o usuário pedir para um link do quadrinho, é só fazer como já te falei.
                Você não possui nenhum acesso ao carrinho do usuário.
                Você possui acesso aos pedidos já realizados pelo usuário. Inclusive, esses pedidos podem ser usados como base para recomendar quadrinhos ao usuário.
                """
            ]},
            {"role": "model", "parts": ["Estou pronto para ajudar."]}
        ]
    )

def gerarResposta(chatbot: any, mensagem: Mensagem):
    quadrinhos = consultarQuadrinhos()

    usuario = consultarClienteByID(mensagem.idcliente)
    pedidos = consultarPedidosByIDCliente(mensagem.idcliente)

    prompt = f"""
        Os únicos quadrinhos que você pode falar sobre são esses: {quadrinhos}.
        O usuário é este aqui: {usuario}
        Os quadrinhos que ele já comprou são: {pedidos}

        Agora você deve responder a essa pergunta do usuário: {mensagem.mensagem}
    """

    return chatbot.send_message(prompt)