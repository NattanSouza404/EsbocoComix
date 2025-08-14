import { estourarErroAPI } from "./alertErro.js";

const PATH = '/api/carrinho';

export async function retornarCarrinho() {
    const resposta = await fetch(PATH);

    if (resposta.status !== 200){
        await estourarErroAPI(resposta);
    }

    return await resposta.json();
}

export async function adicionarItemAoCarrinho(item){
    const option = {
        method: 'POST',
        headers:{'Content-Type': 'application/json'},
        body: JSON.stringify(item)
    }

    const resposta = await fetch(PATH, option);

    if (resposta.status !== 201) {
        await estourarErroAPI(resposta);
    }
}

export async function atualizarItemCarrinho(item){
    const option = {
        method: 'PUT',
        headers:{'Content-Type': 'application/json'},
        body: JSON.stringify(item)
    }

    const resposta = await fetch(PATH, option);

    if (resposta.status !== 200) {
        await estourarErroAPI(resposta);
    }
}

export async function  deletarItemCarrinho(item){
    const option = {
        method: 'DELETE',
        headers:{'Content-Type': 'application/json'},
        body: JSON.stringify(item)
    }

    const resposta = await fetch(PATH, option);

    if (resposta.status !== 204) {
        await estourarErroAPI(resposta);
    }
}