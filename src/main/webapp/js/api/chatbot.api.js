import { localStorageKeys } from "../localStorage.js";
import { estourarErroAPI } from "./alertErro.js";

const PATH = "/api/chatbot";

export async function retornarRespostaIA(mensagem){
    const url = `${PATH}/prompt-mensagem`;

    const option = {
        method: 'POST',
        headers:{'Content-Type': 'application/json'},
        body: JSON.stringify(
            { 
                idcliente: localStorage.getItem(localStorageKeys.idCliente),
                mensagem: mensagem,
            }
        )
    }

    const resposta = await fetch(url, option);

    if (resposta.status !== 200) {
        await estourarErroAPI(resposta);
    }

    return resposta.json();
}