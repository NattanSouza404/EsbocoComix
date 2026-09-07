import { ChatIA } from "@componentes/chatIA.js";

export const ContainerNav = () => {
    const chatIA = new ChatIA();

    const containerNav = document.createElement('div');

    containerNav.className = "container";
    containerNav.innerHTML = /* html */`
    
        <a id="logo-nav" class="navbar-brand" href="/">
            <img src="/img/logo.png">
            <h5 class="fw-bold">Livraria de<br/>Quadrinhos</h5>
        </a>

        <button class="navbar-toggler" type="button"
            data-bs-toggle="collapse"
            data-bs-target="#navbar-nav"
            aria-controls="navbar-nav"
            aria-expanded="false"
            aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div id="navbar-nav" class="collapse navbar-collapse">
            <ul class="navbar-nav ms-auto">
                <li class="nav-link">
                    <div class="dropdown">
                        <img src="/img/imagem-do-usuario-com-fundo-preto.png">
                        
                        <button
                            class="btn dropdown-toggle"
                            type="button"
                            data-bs-toggle="dropdown"
                            aria-expanded="false"
                        >
                            Minha Conta
                        </button>
                        
                        <ul class="dropdown-menu">
                            <li>
                                <a class="dropdown-item" href="/conta">
                                    Conta
                                </a>
                            </li>
                            <li>
                                <a class="dropdown-item" href="/login">
                                    Sair
                                </a>
                            </li>
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
                        <img src="/img/bag.svg"> Compras
                    </a>
                </li>
                <li class="nav-item">
                    <a id="abrir-chat-ia" class="nav-link">
                        <img src="/img/bate-papo-com-ia.png"> Assistente Virtual
                    </a>
                </li>
                <li class="nav-item">
                    <div class="dropdown">
                        <button
                            class="btn position-relative dropdown-toggle"
                            type="button"
                            data-bs-toggle="dropdown"
                            aria-expanded="false"
                        >
                            <img
                                src="/img/bell.svg"
                                alt="Bell Icon"
                                width="30"
                            >
                            <span
                                id="notificationCount" 
                                class="position-absolute top-0 start-100 translate-middle badge rounded-pill bg-danger"
                            >
                                3
                            </span>
                        </button>
                        <ul
                            id="notificationsDropdown" 
                            class="dropdown-menu dropdown-menu-end"
                        >
                            <li class="dropdown-item">
                                "Conancraft" removido do carrinho: Fora de estoque!
                            </li>
                            <li class="dropdown-item">
                                Bloqueio do "Menino Maluquinho" irá expirar em 5 minutos!
                            </li>
                            <li class="dropdown-item">
                                Troca para o pedido #61 aceita!
                            </li>
                        </ul>
                    </div>
                </li>
            </ul>
        </div>
    `;

    /** @type {HTMLButtonElement} */
    (containerNav.querySelector('#abrir-chat-ia')).onclick = () => {
        chatIA.show();
    }

    const notificationCount = containerNav.querySelector('#notificationCount');
    const notificationsDropdown = containerNav.querySelector('#notificationsDropdown');

    const clearNotifications = () => {
        notificationCount.textContent = '';
    };

    notificationsDropdown.addEventListener('click', clearNotifications);

    return containerNav;
}