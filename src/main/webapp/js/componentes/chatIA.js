import { Modal } from "./modal.js";
import { retornarRespostaIA } from "../api/apiChatbot.js";

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

        this.adicionarMensagem('Bom dia! O que você gostaria de saber?', 'texto-ia');
        this.adicionarMensagem('Gostaria de alguma recomendação específica?', 'texto-ia');
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

        div.querySelector('p').textContent = mensagem;

        this.modalElement.querySelector('#historico-conversa').append(div);
    }

    async enviarMsgChatIA(){
        const textarea = document.querySelector('.caixa-mensagem-usuario');
        const mensagem = textarea.value;
        if (mensagem === null || mensagem.trim().length === 0){
            return;
        }
    
        this.adicionarMensagem(mensagem, 'texto-usuario');

        const mensagemIA = await retornarRespostaIA(mensagem);

        if (mensagemIA !== undefined && mensagem !== null && mensagem !== ''){
            this.adicionarMensagem(mensagemIA.resposta, 'texto-ia');
        } else {
            this.adicionarMensagem("[Assistente virtual fora de serviço]", 'texto-ia');
        }

        textarea.value = '';
    }
}

function ConteudoModal(){
    const div = document.createElement("div");
    div.id = "historico-conversa";
    return div;
}