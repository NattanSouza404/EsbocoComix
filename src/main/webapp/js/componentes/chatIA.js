export class ChatIA extends HTMLElement {

    frasesProntas = [
        'De acordo com o seu perfil, acredito que o quadrinho Ficticio, do autor Jr pode te interessar!',
        'Vi que você tem interesse em histórias de ação. Estou correto?',
        'De acordo com a base de dados que tenho, o livro que você deve estar procurando se chama A História Sem Começo, de Jorge Bolaños.',
        'Caso tiver interesse em alguma recomendação, sinta-se livre para perguntar!'
    ];

    constructor(){
        super();

        this.contador = 0;

        this.id = 'chat-ia';
        this.style.display = 'none';

        this.enviarMsgChatIA = this.enviarMsgChatIA.bind(this);

        this.chatHeader = this.initHeader();
        this.historicoConversa = this.initHistoricoConversa();
        this.enviarMensagem = this.initEnviarMensagem();

        this.append(this.chatHeader);
        this.append(this.historicoConversa);
        this.append(this.enviarMensagem);

        this.adicionarMensagem('Bom dia! O que você gostaria de saber?', 'texto-ia');
        this.adicionarMensagem('Gostaria de alguma recomendação específica?', 'texto-ia');
    }

    initHeader(){
        const chatHeader = document.createElement('div');
        chatHeader.id = 'chat-ia-header';

        let p = document.createElement('p');
        p.textContent = "ASSISTENTE VIRTUAL";
        chatHeader.append(p);

        return chatHeader;
    }

    initHistoricoConversa(){
        const historicoConversa = document.createElement('div');
        historicoConversa.id = 'historico-conversa';
        return historicoConversa;
    }

    initEnviarMensagem(){
        const enviarMensagem = document.createElement('div');
        enviarMensagem.id = "enviar-mensagem";

        const textarea = document.createElement('textarea');
        textarea.placeholder = "Digite uma mensagem";
        textarea.addEventListener(
            "keypress", 
            e => {
                if (e.key === "Enter") {
                    e.preventDefault();
                    this.enviarMsgChatIA();
                }
            }
        );

        const button = document.createElement('button');
        button.onclick = () => this.enviarMsgChatIA();
        
        const img = document.createElement('img');
        img.src = "/img/enviado.png";
        button.append(img);
        
        enviarMensagem.append(textarea);
        enviarMensagem.append(button);
    
        return enviarMensagem;
    }

    trocarDisplayChat() {
        if (this.style.display === 'none'){
            this.style.display = 'flex';
        } else {
            this.style.display = 'none';
        }
    }

    adicionarMensagem(mensagem, tipoMensagem){
        const div = document.createElement('div');
        div.className = tipoMensagem;
    
        const p = document.createElement('p');
        p.textContent = mensagem;
        
        const img = document.createElement('img');
        if (tipoMensagem === 'texto-ia'){
            img.src = '/img/bate-papo-com-ia.png';
        } else {
            img.src = '/img/imagem-do-usuario-com-fundo-preto.png';
        }
    
        div.append(img);
        div.append(p);
        
        this.historicoConversa.append(div);
    }

    enviarMsgChatIA(){
        const textarea = document.querySelector('#enviar-mensagem textarea');
        const mensagem = textarea.value;
        if (mensagem === null || mensagem.trim().length === 0){
            return;
        }
    
        textarea.value = '';
        this.adicionarMensagem(mensagem, 'texto-usuario');

        if (this.contador >= this.frasesProntas.length){
            this.contador = 0;
        }
    
        this.adicionarMensagem(this.frasesProntas.at(this.contador), 'texto-ia');
        this.contador++;
    }
}

customElements.define('chat-ia', ChatIA);