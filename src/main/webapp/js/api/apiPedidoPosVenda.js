import { estourarErroAPI } from "./alertErro.js";

const PATH = "/api/pedido_pos_venda";

export async function retornarPedidosPosVenda(){
    const resposta = await fetch(PATH);

    if (resposta.status !== 200){
        await estourarErroAPI(resposta);
    }

    return await resposta.json();
}

export async function retornarPedidosPosVendaByCliente(idCliente){
    let url = `${PATH}?idcliente=${idCliente}`;

    const resposta = await fetch(url);

    if (resposta.status !== 200){
        await estourarErroAPI(resposta);
    }

    return await resposta.json();
}

export async function inserirPedidoPosVenda(pedido) {
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

export async function atualizarStatusPedidoPosVenda(pedido){
    let url = PATH;

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