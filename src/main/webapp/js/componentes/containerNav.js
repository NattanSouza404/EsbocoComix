import { ChatIA } from "./chatIA.js";

export class ContainerNav extends HTMLDivElement {
    constructor(){
        super();

        this.chatIA = new ChatIA();

        this.className = "container";
        this.innerHTML = `
        
            <a id="logo-nav" class="navbar-brand" href="/index">
                <img src="/img/logo.png">
                <h5 class="fw-bold">Livraria de<br/>Quadrinhos</h5>
            </a>

            <div id="navbar-nav" class="collapse navbar-collapse">
                <ul class="navbar-nav ms-auto">
                    <li class="nav-link">
                        <div class="dropdown">
                            <img src="/img/imagem-do-usuario-com-fundo-preto.png">
                            <button class="btn dropdown-toggle" type="button" data-bs-toggle="dropdown" aria-expanded="false">
                                Minha Conta
                            </button>
                            <ul class="dropdown-menu">
                                <li><a class="dropdown-item" href="/conta">Conta</a></li>
                                <li><a class="dropdown-item" href="/login">Sair</a></li>
                            </ul>
                        </div>
                    </li>
                    <li class="nav-item">
                        <a id="link-carrinho" href="/carrinho" class="nav-link">
                            <img src="/img/carrinho.png"> Carrinho
                        </a>
                    </li>
                    <li class="nav-item">
                        <a href="/minhasCompras" class="nav-link">
                            <img src="/img/bag.svg">
                             Compras
                         </a>
                    </li>
                    <li class="nav-item">
                        <a id="abrir-chat-ia" class="nav-link">
                            <img src="/img/bate-papo-com-ia.png"> Assistente Virtual
                        </a>
                    </li>
                    <li class="nav-item">
                        <div class="dropdown">
                            <button class="btn position-relative dropdown-toggle" type="button" data-bs-toggle="dropdown" aria-expanded="false">
                                <img src="/img/bell.svg" alt="Bell Icon" width="30">
                                <span class="position-absolute top-0 start-100 translate-middle badge rounded-pill bg-danger" id="notificationCount">
                                    3
                                </span>
                            </button>
                            <ul class="dropdown-menu dropdown-menu-end" id="notificationsDropdown">
                                <li class="dropdown-item">"Conancraft" removido do carrinho: Fora de estoque!</li>
                                <li class="dropdown-item">Bloqueio do "Menino Maluquinho" irá expirar em 5 minutos!</li>
                                <li class="dropdown-item">Troca para o pedido #61 aceita!</li>
                            </ul>
                        </div>
                    </li>
                </ul>
            </div>
        `;

        this.querySelector('#abrir-chat-ia').onclick = () => {
            this.chatIA.show();
        }

        const notificationCount = this.querySelector('#notificationCount');
        const notificationsDropdown = this.querySelector('#notificationsDropdown');

        const clearNotifications = () => {
            notificationCount.textContent = '';
        };

        notificationsDropdown.addEventListener('click', clearNotifications);
    }
}

customElements.define("main-nav", ContainerNav, { extends: 'div' });