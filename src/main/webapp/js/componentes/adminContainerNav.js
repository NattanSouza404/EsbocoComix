export class AdminContainerNav extends HTMLDivElement {
    constructor() {
        super();
        this.className = "container";
        this.innerHTML = `
            <div class="container-fluid">
            <a class="navbar-brand fw-bold" href="#">Painel de Controle</a>
            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav me-auto">
                    <li class="nav-item">
                        <a href="/admin/clientes" class="nav-link active" aria-current="page">Clientes</a>
                    </li>
                    <li class="nav-item">
                        <a href="/admin/estoque" class="nav-link">Controle de Estoque</a>
                    </li>
                    <li class="nav-item">
                        <a href="/admin/analise" class="nav-link">Relatórios de Análise</a>
                    </li>
                    <li class="nav-item">
                        <a href="/admin/gerenciarVendas" class="nav-link">Gerenciar Vendas</a>
                    </li>
                </ul>
                <a href="/login" class="btn btn-danger">Sair</a>
            </div>
        </div>
        `
    }

}

customElements.define("admin-container-nav", AdminContainerNav, { extends: "div" });