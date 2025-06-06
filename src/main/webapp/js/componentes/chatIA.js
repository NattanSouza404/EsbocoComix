import { Modal } from "./modal.js";
import { retornarRespostaIA } from "../api/apiChatbot.js";
import { adicionarMensagemHistorico, getHistorico, removerHistorico } from "../localStorage.js";

export class ChatIA extends Modal {

    constructor(){
        const conteudoModal = ConteudoModal();

        super('modal-chat-ia', "Assistente Virtual", conteudoModal);

        this.conteudoModal = conteudoModal;

        this.modalElement.querySelector('.modal-footer').innerHTML = `
            <textarea class="caixa-mensagem-usuario"></textarea>
            <button class="btn-enviar">
                <img src="/img/enviado.png">
            </button>

            <button class="btn-limpar-chat">
                Limpar chat
            </button>
        `;

        this.modalElement.querySelector('textarea').addEventListener(
            "keypress", 
            e => {
                if (e.key === "Enter") {
                    e.preventDefault();
                    this.enviarMsgChatIA();
                }
            }
        );

        this.modalElement.querySelector('.btn-enviar').onclick = () => {
            this.enviarMsgChatIA();
        };

        this.modalElement.querySelector('.btn-limpar-chat').onclick = () => {
            this.limparChat();
        };

        getHistorico().forEach(h => {
            this.adicionarMensagem(h.mensagem, h.tipoMensagem)
        });
    }

    adicionarMensagem(mensagem, tipoMensagem){
        let imgPath = tipoMensagem === 'texto-ia' ?
            '/img/bate-papo-com-ia.png' : '/img/imagem-do-usuario-com-fundo-preto.png';

        const div = document.createElement('div');
        div.className = tipoMensagem;
        div.innerHTML = `
            <img src="${imgPath}"></img>
            <p></p>
        `;

        const p = div.querySelector('p');

        const linkRegex = /(\/anuncio\?id=\d+)/g;

        const parts = mensagem.split(linkRegex);

        parts.forEach(part => {
            if (linkRegex.test(part)) {
                const link = document.createElement('a');
                link.href = part;
                link.textContent = "Anúncio";
                link.target = '_blank';
                p.appendChild(link);
                return;
            }

            const span = document.createElement('span');
            span.textContent = part;
            p.appendChild(span);
        });

        this.modalElement.querySelector('#historico-conversa').append(div);

        return p.textContent;
    }

    limparChat(){
        if (confirm("Deseja realmente deletar o histórico de mensagens? Essa ação não poderá ser revertida.")){
            removerHistorico();
            window.location.reload();
        }
    }

    async enviarMsgChatIA(){
        const textarea = document.querySelector('.caixa-mensagem-usuario');
        const mensagem = textarea.value;
        if (mensagem === null || mensagem.trim().length === 0){
            return;
        }
    
        this.adicionarMensagem(mensagem, 'texto-usuario');

        const mensagemIA = await retornarRespostaIA(mensagem);

        const mensagemFormatada = 
            mensagemIA !== undefined && mensagem !== null && mensagem !== '' ?
            this.adicionarMensagem(mensagemIA.resposta, 'texto-ia') : this.adicionarMensagem("[Assistente virtual fora de serviço]", 'texto-ia');

        textarea.value = '';

        this.scrollar();

        adicionarMensagemHistorico(mensagem, 'texto-usuario');
        adicionarMensagemHistorico(mensagemFormatada, 'texto-ia');
    }

    scrollar(){
        const modalBody = this.modalElement.querySelector('.modal-body');
        modalBody.scrollTop = modalBody.scrollHeight; 
    }
}

function ConteudoModal(){
    const div = document.createElement("div");
    div.id = "historico-conversa";
    return div;
}