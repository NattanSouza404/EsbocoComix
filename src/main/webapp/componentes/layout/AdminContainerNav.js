export const AdminContainerNav = () => {
    const div = document.createElement('div');
    div.className = "container";
    div.innerHTML = /* html */ `
        <div class="container-fluid">
            <a class="navbar-brand fw-bold" href="#">
                Painel de Controle
            </a>
            
            <div class="collapse navbar-collapse" id="navbarNav">

                <ul class="navbar-nav me-auto">
                    <li class="nav-item">
                        <a
                            id="link-clientes"
                            class="nav-link"
                            href="/admin/clientes"
                            aria-current="page"
                        >
                            Clientes
                        </a>
                    </li>
                    <li class="nav-item">
                        <a
                            id="link-estoque"
                            href="/admin/estoque"
                            class="nav-link"
                        >
                            Controle de Estoque
                        </a>
                    </li>
                    <li class="nav-item">
                        <a
                            id="link-analise"
                            href="/admin/analise"
                            class="nav-link"
                        >
                            Relatórios de Análise
                        </a>
                    </li>
                    <li class="nav-item">
                        <a
                            id="link-gerenciar-vendas"
                            href="/admin/gerenciarVendas"
                            class="nav-link"
                        >
                            Gerenciar Vendas
                        </a>
                    </li>
                </ul>

                <a href="/login" class="btn btn-danger">
                    Sair
                </a>
            </div>
        </div>
    `;

    return div;
}