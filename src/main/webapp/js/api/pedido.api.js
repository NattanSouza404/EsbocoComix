import { estourarErroAPI } from "./alertErro.js";

const PATH = "/api/pedido";

export async function retornarPedidos(idCliente){
    let url = PATH;

    if (idCliente){
        url += `?id=${idCliente}`;
    }

    const resposta = await fetch(url);

    if (resposta.status !== 200){
        await estourarErroAPI(resposta);
    }

    return await resposta.json();
}

export async function enviarPedido(pedido) {
    const url = PATH;

    const option = {
        method: 'POST',
        headers:{'Content-Type': 'application/json'},
        body: JSON.stringify(pedido)
    }

    const resposta = await fetch(url, option);

    if (resposta.status !== 201) {
        await estourarErroAPI(resposta);
    }
}

export async function atualizarStatusPedido(pedido){
    let url = `${PATH}/atualizar-status`;

    const option = {
        method: 'PUT',
        headers:{'Content-Type': 'application/json'},
        body: JSON.stringify(pedido)
    }

    const resposta = await fetch(url, option);

    if (resposta.status !== 200) {
        await estourarErroAPI(resposta);
    }
}