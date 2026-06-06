import { formatarPreco } from "../../script.js";
import { TabelaInfoProduto } from "./TabelaInfoProduto.js";

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

