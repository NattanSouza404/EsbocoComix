export const localStorageKeys = {
    historicoChatIA: "historico-chat-ia",
    idCliente: "idcliente",
};

export function getHistorico(){
    let historicoStr = localStorage.getItem(localStorageKeys.historicoChatIA);

    if (historicoStr === null || historicoStr === undefined) {
        const novaMensagem = [
            {
                mensagem: 'Olá! Como posso ajudar?',
                tipoMensagem: "texto-ia"
            }
        ];
        localStorage.setItem(localStorageKeys.historicoChatIA, JSON.stringify(novaMensagem));
    } else {
        return JSON.parse(historicoStr);
    }

    return JSON.parse(localStorage.getItem(localStorageKeys.historicoChatIA));
}

export function adicionarMensagemHistorico(mensagem, tipoMensagem){
    const historico = getHistorico();

    historico.push(
        {
            mensagem: mensagem,
            tipoMensagem: tipoMensagem
        }
    )

    localStorage.setItem(localStorageKeys.historicoChatIA, JSON.stringify(historico));
}

export function removerHistorico(){
    localStorage.removeItem(localStorageKeys.historicoChatIA);
}

