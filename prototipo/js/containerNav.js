import { ChatIA } from "./chat_ia.js";
import { carregarBootstrapJS } from "./script.js";

export class ContainerNav extends HTMLDivElement {
    constructor(){
        super();
        this.className = "container";
        this.innerHTML = `
            <a id="logo-nav" class="navbar-brand" href="index.html">
                <img src="img/logo.png">
                <h5 class="fw-bold">Livraria de<br/>Quadrinhos</h5>
            </a>
            <div id="navbar-nav" class="collapse navbar-collapse">
                <ul class="navbar-nav ms-auto">
                    <li class="nav-link">
                        <div class="dropdown">
                            <img src="img/imagem-do-usuario-com-fundo-preto.png">
                            <button class="btn dropdown-toggle" type="button" data-bs-toggle="dropdown" aria-expanded="false">
                                Minha Conta
                            </button>
                            <ul class="dropdown-menu">
                                <li><a class="dropdown-item" href="conta.html">Conta</a></li>
                                <li><a class="dropdown-item" href="login.html">Sair</a></li>
                            </ul>
                        </div>
                    </li>
                    <li class="nav-item">
                        <a href="carrinho.html" class="nav-link"><img src="img/carrinho.png"> Carrinho</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" onclick="chatIA.trocarDisplayChat()">
                        <img src="img/bate-papo-com-ia.png"> Assistente Virtual
                        </a>
                    </li>
                </ul>
            </div>
        `;
    }
}

customElements.define("main-nav", ContainerNav, { extends: 'div' });

const chatIA = new ChatIA();
document.body.append(chatIA);
window.chatIA = chatIA;

chatIA.adicionarMensagem('Bom dia! O que você gostaria de saber?', 'texto-ia');
chatIA.adicionarMensagem('Gostaria de alguma recomendação específica?', 'texto-ia');

carregarBootstrapJS();