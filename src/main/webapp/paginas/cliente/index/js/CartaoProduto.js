import { formatarPreco } from "/js/script.js";

export class CartaoProduto extends HTMLDivElement {
    constructor(quadrinho){
        super();

        this.className = 'col-md-3';

        this.innerHTML = `
            <div class="card">
              <img src="${quadrinho.urlImagem}" class="card-img-top" alt="Comic Cover">
              <div class="card-body text-center">
                <h5 class="card-title">${quadrinho.titulo}</h5>
                <p class="card-text">${quadrinho.autor}</p>
                <p class="price text-success fw-bold">${formatarPreco(quadrinho.preco)}</p>
                <a href="/anuncio?id=${quadrinho.id}" class="btn btn-warning text-white">Comprar</a>
              </div>
            </div>
        `
    }
}

customElements.define('cartao-produto', CartaoProduto, { extends: "div" });