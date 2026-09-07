import { estourarErroAPI } from "./alertErro.js";

const PATH = "/api/estoque";

export async function inserirEntradaEstoque(entradaEstoque) {
    const url = PATH;

    const option = {
        method: 'POST',
        headers:{'Content-Type': 'application/json'},
        body: JSON.stringify(entradaEstoque)
    }

    const resposta = await fetch(url, option);

    if (resposta.status !== 201) {
        await estourarErroAPI(resposta);
    }

}

export async function consultarEntradasEstoque(){
    const resposta = await fetch(PATH);

    if (resposta.status !== 200){
        await estourarErroAPI(resposta);
    }

    return await resposta.json();
}