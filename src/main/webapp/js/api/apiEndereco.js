import { estourarErroAPI } from "./alertErro.js";

const PATH = "/api/endereco";

export async function retornarEnderecos(idCliente){
    const resposta = await fetch(`${PATH}?idcliente=${idCliente}`);

    if (resposta.status !== 200){
        await estourarErroAPI(resposta);
    }

    return await resposta.json();
}

export async function inserirEndereco(endereco){
    const url = PATH;

    const option = {
        method: 'POST',
        headers:{'Content-Type': 'application/json'},
        body: JSON.stringify(endereco)
    }

    const resposta = await fetch(url, option);

    if (resposta.status !== 201) {
        await estourarErroAPI(resposta);
    }
} 

export async function atualizarEndereco(endereco){ 
    let url = PATH;

    const option = {
        method: 'PUT',
        headers:{'Content-Type': 'application/json'},
        body: JSON.stringify(endereco)
    }

    const resposta = await fetch(url, option);

    if (resposta.status !== 200) {
        await estourarErroAPI(resposta);
    }
}

export async function deletarEndereco(endereco){
    const url = PATH;

    const option = {
        method: 'DELETE',
        headers:{'Content-Type': 'application/json'},
        body: JSON.stringify(endereco)
    }

    const resposta = await fetch(url, option);

    if (resposta.status !== 204) {
        await estourarErroAPI(resposta);
    }
}