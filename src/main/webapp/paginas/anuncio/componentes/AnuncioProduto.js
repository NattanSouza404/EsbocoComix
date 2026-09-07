import { formatarData, formatarPreco } from "../../../js/script.js";

export const AnuncioProduto = (quadrinho, adicionarItem) => {
    const anuncio = document.createElement('div');
    anuncio.className = "card shadow-lg";
    
    anuncio.innerHTML = /* html */ `
        <div class="row g-0">

            <section 
                id="secao-imagem"
                class="col-md-6"
            >
                <img src="${quadrinho.urlImagem}"
                    class="img-fluid rounded-start"
                    alt="Imagem do produto"
                >
            </section>

            <section id="secao-principal" class="col-md-6">
                <div class="card-body">
                
                    <section id="header-produto"
                        class="d-flex flex-column align-items-center gap-3"
                    >
                        <h1 class="card-title">
                            ${quadrinho.titulo}
                        </h1>

                        <p class="card-text fs-4 text-success">
                            ${formatarPreco(quadrinho.preco)}
                        </p>
                    </section>

                    <hr class="my-4">

                    <section
                        id="adicionar-carrinho"
                        class="d-flex flex-column align-items-center gap-3"
                    >
                        <div class="d-flex gap-3 w-75">
                            <button
                                class="btn btn-adicionar-item btn-warning btn-lg"
                            >
                                Adicionar ao carrinho
                            </button>

                            <input
                                class="form-control"
                                name="quantidade"
                                placeholder="Quantidade"
                                type="number"
                                value=1
                                min="1"
                            >
                        </div>

                        <p id="estoque">
                            ${
                                quadrinho.quantidadeEstoque > 0 ? 
                                    `Estoque: ${quadrinho.quantidadeEstoque}` : `<p>Fora de estoque</p>`
                            }
                        </p>
                    </section>
                
                    <hr class="my-4">

                    <h3 class="mt-4">Detalhes</h3>
                </div>
            </section>
        </div>
    `;

    anuncio.querySelector('.card-body').append(
        TabelaInfoProduto(quadrinho)
    );

    /** @type {HTMLButtonElement} */
    (anuncio.querySelector('.btn-adicionar-item')).onclick = () => {
        adicionarItem(quadrinho);
    }

    return anuncio;
}

function TabelaInfoProduto(quadrinho){
    const tabela = document.createElement('table');
    tabela.className = "table table-hover";

    const categorias = (quadrinho.categorias ?? [])
        .map(c => c.nome)
        .join(", ");

    tabela.innerHTML =  /* html */ `
        <tbody>
            <tr>
                <td>Ano: ${formatarData(quadrinho.ano)}</td>
            </tr>
            <tr>
                <td>Autor: ${quadrinho.autor}</td>
            </tr>
            <tr>
                <td>Editora: ${quadrinho.editora}</td>
            </tr>
            <tr>
                <td>Edição: ${quadrinho.edicao}</td>
            </tr>
            <tr>
                <td>ISBN: ${quadrinho.isbn}</td>
            </tr>
            <tr>
                <td>Número de Páginas: ${quadrinho.numeroPaginas}</td>
            </tr>
            <tr>
                <td>Sinopse: ${quadrinho.sinopse}</td>
            </tr>
            <tr>
                <td>Dimensões: ${quadrinho.altura}cm X ${quadrinho.largura}cm X ${quadrinho.profundidade}cm</td>
            </tr>
            <tr>
                <td>Peso: ${quadrinho.peso}g</td>
            </tr>
            <tr>
                <td>Código de Barras: ${quadrinho.codigoBarras}</td>
            </tr>
            <tr>
                <td>Categorias: ${categorias}</td>
            </tr>
            <tr>
                <td>Grupo de precificação: ${quadrinho.grupoPrecificacao.nome}
            </tr>
        </tbody>
    `;

    return tabela;
}
