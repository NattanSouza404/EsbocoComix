import requests

urls = [
    "http://localhost:8080",            # para quando roda localmente
    "http://host.docker.internal:8080", # para quando roda no Docker
]

def consultarQuadrinhos():
    for url in urls:
        try:
            resposta = requests.get(
                f"{url}/api/quadrinho", 
                timeout=2
            )
            resposta.raise_for_status()
            return resposta.json()
        except Exception as e:
            print(f"Tentativa falhou para {url}: {e}")

    raise ConnectionError("Não foi possível conectar ao serviço de quadrinhos.")

def consultarClienteByID(idcliente):
    for url in urls:
        try:
            resposta = requests.get(
                f"{url}/api/cliente?id={idcliente}",
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
            print(f"Tentativa falhou para {url}: {e}")

    raise ConnectionError("Não foi possível conectar ao serviço de clientes.")

def consultarPedidosByIDCliente(idcliente):
    for url in urls:
        try:
            resposta = requests.get(
                f"{url}/api/pedido?idcliente={idcliente}", 
                timeout=2
            )
            resposta.raise_for_status()
            return resposta.json()
        except Exception as e:
            print(f"Tentativa falhou para {url}: {e}")

    raise ConnectionError("Não foi possível conectar ao serviço de pedidos.")