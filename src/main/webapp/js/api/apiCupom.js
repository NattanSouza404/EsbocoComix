import { estourarErroAPI } from "./alertErro.js";

const PATH = "/api/cupom";

export async function retornarCupons(idCliente){
    const resposta = await fetch(`${PATH}?idcliente=${idCliente}`);

    if (resposta.status !== 200){
        await estourarErroAPI(resposta);
    }

    return await resposta.json();
}

export async function inserirCupom(cupom){
    const url = PATH;

    const option = {
        method: 'POST',
        headers:{'Content-Type': 'application/json'},
        body: JSON.stringify(cupom)
    }

    const resposta = await fetch(url, option);

    if (resposta.status !== 201) {
        await estourarErroAPI(resposta);
    }
} 