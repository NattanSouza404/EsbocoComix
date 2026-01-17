import { estourarErroAPI } from "./alertErro.js";

const PATH = "/api/analise";

export async function retornarAnalise(filtro) {
    let url = PATH;

    if (filtro){
        url += `?dataInicio=${filtro.dataInicio}`;
        url += `&dataFinal=${filtro.dataFinal}`;
    }

    const resposta = await fetch(url);

    if (resposta.status !== 200){
        await estourarErroAPI(resposta);
    }

    return await resposta.json();
}