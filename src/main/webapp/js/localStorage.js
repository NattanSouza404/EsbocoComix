export function getHistorico(){
    let historico = localStorage.getItem("historico-chat-ia");

    if (historico === null || historico === undefined) {
        historico = [
            {
                mensagem: 'Olá! Como posso ajudar?',
                tipoMensagem: "texto-ia"
            }
        ]
        localStorage.setItem("historico-chat-ia", JSON.stringify(historico));
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

    localStorage.setItem("historico-chat-ia", JSON.stringify(historico));
}

export function removerHistorico(){
    localStorage.removeItem('historico-chat-ia');
}

