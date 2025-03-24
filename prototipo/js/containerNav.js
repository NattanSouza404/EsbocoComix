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
                <li class="nav-item">
                    <a href="conta.html" class="nav-link"><img src="img/conta.png"> Conta</a>
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