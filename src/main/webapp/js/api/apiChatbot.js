import { estourarErroAPI } from "./alertErro.js";

const PATH = "http://localhost:8000/";

export async function retornarRespostaIA(mensagem){
    const url = `${PATH}get-message`;

    const option = {
        method: 'POST',
        headers:{'Content-Type': 'application/json'},
        body: JSON.stringify(
            { 
                idcliente: localStorage.getItem('idcliente'),
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