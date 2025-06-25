import requests

def consultarQuadrinhos():
    url = "http://localhost:8080/api/quadrinho"

    resposta = requests.get(url).json()

    return resposta

def consultarClienteByID(idcliente):
    url = f"http://localhost:8080/api/cliente?id={idcliente}"

    resposta = requests.get(url).json()

    return {
        "nome": resposta["nome"],
        "genero": resposta["genero"],
        "dataNascimento": resposta["dataNascimento"]
    }

def consultarPedidosByIDCliente(idcliente):
    url = f"http://localhost:8080/api/pedido?idcliente={idcliente}"

    resposta = requests.get(url).json()

    return resposta 
