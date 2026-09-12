const keys = {
    historicoChatIA: "historico-chat-ia",
};

export class ChatIAStorage {
    static getHistorico(){
        let historicoStr = localStorage.getItem(keys.historicoChatIA);

        if (historicoStr === null || historicoStr === undefined) {
            const novaMensagem = [
                {
                    mensagem: 'Olá! Como posso ajudar?',
                    tipoMensagem: "texto-ia"
                }
            ];
            localStorage.setItem(keys.historicoChatIA, JSON.stringify(novaMensagem));
        } else {
            return JSON.parse(historicoStr);
        }

        return JSON.parse(localStorage.getItem(keys.historicoChatIA));
    }

    static adicionarMensagemHistorico(mensagem, tipoMensagem){
        const historico = this.getHistorico();

        historico.push(
            {
                mensagem: mensagem,
                tipoMensagem: tipoMensagem
            }
        )

        localStorage.setItem(keys.historicoChatIA, JSON.stringify(historico));
    }

    static removerHistorico(){
        localStorage.removeItem(keys.historicoChatIA);
    }
}
