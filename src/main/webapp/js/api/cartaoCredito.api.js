import { estourarErroAPI } from "./alertErro.js";

const PATH = "/api/cartaocredito";

export async function retornarCartoesCredito(idCliente) {
    const resposta = await fetch(`${PATH}/por-id-cliente?id=${idCliente}`);

    if (resposta.status !== 200){
        await estourarErroAPI(resposta);
    }

    return await resposta.json();
}

export async function inserirCartaoCredito(cartao){
    const option = {
        method: 'POST',
        headers:{'Content-Type': 'application/json'},
        body: JSON.stringify(cartao)
    }

    const resposta = await fetch(PATH, option);

    if (resposta.status !== 201) {
        await estourarErroAPI(resposta);
    }
} 

export async function atualizarCartaoCredito(cartaoCredito){
    const option = {
        method: 'PUT',
        headers:{'Content-Type': 'application/json'},
        body: JSON.stringify(cartaoCredito)
    }

    const resposta = await fetch(PATH, option);

    if (resposta.status !== 200) {
        await estourarErroAPI(resposta);
    }
}

export async function deletarCartaoCredito(cartaoCredito){
    const option = {
        method: 'DELETE',
        headers:{'Content-Type': 'application/json'},
        body: JSON.stringify(cartaoCredito)
    }

    const resposta = await fetch(PATH, option);

    if (resposta.status !== 204) {
        await estourarErroAPI(resposta);
    }
}