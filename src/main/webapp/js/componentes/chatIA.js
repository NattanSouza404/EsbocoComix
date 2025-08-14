import { Modal } from "./modal.js";
import { retornarRespostaIA } from "../api/apiChatbot.js";
import { adicionarMensagemHistorico, getHistorico, removerHistorico } from "../localStorage.js";

export class ChatIA extends Modal {

    constructor(){
        const conteudoModal = ConteudoModal();

        super('modal-chat-ia', "Assistente Virtual", conteudoModal);

        this.conteudoModal = conteudoModal;

        this.modalElement.querySelector('.modal-footer').innerHTML = `
            <div class="dropdown">
                <button class="btn dropdown-toggle" type="button" data-bs-toggle="dropdown" aria-expanded="false">
                    <img src="/img/three-dots.svg">
                </button>
                <ul class="dropdown-menu">
                    <li>
                        <a id="btn-limpar-chat" class="dropdown-item" >
                            Limpar chat
                        </a>
                    </li>
                </ul>
            </div>
        
            <textarea class="caixa-mensagem-usuario"></textarea>

            <button class="btn-enviar">
                <img src="/img/enviado.png">
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

        this.modalElement.querySelector('#btn-limpar-chat').onclick = () => {
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

        const linkMarkdownRegex = /\[([^\]]+)\]\((\/anuncio\?id=\d+)\)/g;

        let mensagemFormatada = mensagem.replace(linkMarkdownRegex, (match, texto, url) => {
            return `<a href="${url}" target="_blank">${texto}</a>`;
        });

        mensagemFormatada = mensagemFormatada.replace(/\*\*(.*?)\*\*/g, '<strong>$1</strong>');

        p.innerHTML = mensagemFormatada;

        this.modalElement.querySelector('#historico-conversa').append(div);
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
        this.scrollar();

        try {
            const mensagemIA = await retornarRespostaIA(mensagem);

            if (mensagemIA === undefined || mensagem === null || mensagem === ''){
                throw new Error("[Assistente virtual fora de serviço]");
            }

            const resposta = mensagemIA.resposta;

            this.adicionarMensagem(resposta, 'texto-ia');

            textarea.value = '';

            this.scrollar();

            adicionarMensagemHistorico(mensagem, 'texto-usuario');
            adicionarMensagemHistorico(mensagemIA.resposta, 'texto-ia');

        } catch (error){
            alert("Erro no assistente virtual: "+error.message);
        }
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