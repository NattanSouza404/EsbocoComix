import requests

from app.config.config import BACKEND_URL

def consultarQuadrinhos():
    try:
        resposta = requests.get(
            f"{BACKEND_URL}/api/quadrinho", 
            timeout=2
        )
        resposta.raise_for_status()
        return resposta.json()
    except Exception as e:
        print(f"Tentativa falhou para {BACKEND_URL}: {e}")

    raise ConnectionError("Não foi possível conectar ao serviço de quadrinhos.")

def consultarClienteByID(idcliente):
    try:
        resposta = requests.get(
            f"{BACKEND_URL}/api/cliente?id={idcliente}",
            timeout=2
        )

        resposta.raise_for_status()

        cliente = resposta.json()

        return {
            "nome": cliente["nome"],
            "genero": cliente["genero"],
            "dataNascimento": cliente["dataNascimento"]
        }
    except Exception as e:
        print(f"Tentativa falhou para {BACKEND_URL}: {e}")

    raise ConnectionError("Não foi possível conectar ao serviço de clientes.")

def consultarPedidosByIDCliente(idcliente):
    try:
        resposta = requests.get(
            f"{BACKEND_URL}/api/pedido?idcliente={idcliente}", 
            timeout=2
        )
        resposta.raise_for_status()
        return resposta.json()
    except Exception as e:
        print(f"Tentativa falhou para {BACKEND_URL}: {e}")

    raise ConnectionError("Não foi possível conectar ao serviço de pedidos.")