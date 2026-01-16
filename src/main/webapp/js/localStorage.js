export const localStorageKeys = {
    historicoChatIA: "historico-chat-ia",
    idCliente: "idcliente",
};

export function getHistorico(){
    let historico = localStorage.getItem(localStorageKeys.historicoChatIA);

    if (historico === null || historico === undefined) {
        historico = [
            {
                mensagem: 'Olá! Como posso ajudar?',
                tipoMensagem: "texto-ia"
            }
        ]
        localStorage.setItem(localStorageKeys.historicoChatIA, JSON.stringify(historico));
    } else {
        historico = JSON.parse(historico);
    }

    return historico;
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

