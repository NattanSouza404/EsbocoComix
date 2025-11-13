import { alertarErro } from "../../../../js/api/alertErro.js";
import { adicionarItemAoCarrinho } from "/js/api/apiCarrinho.js";
import { formatarPreco } from "/js/script.js";

export class CartaoProduto extends HTMLDivElement {
  constructor(quadrinho) {
    super();

    this.className = 'col-md-3';

    this.innerHTML = `
      <div class="card">
        <img src="${quadrinho.urlImagem}" class="card-img-top" alt="Comic Cover">
        <div class="card-body text-center">
          <h5 class="card-title">${quadrinho.titulo}</h5>
          <p class="card-text">${quadrinho.autor}</p>
          <p class="price text-success fw-bold">
            ${
              quadrinho.quantidadeEstoque > 0 
                ? formatarPreco(quadrinho.preco)
                : 'Fora de estoque'
            }
          </p>
        </div>
        <a href="/anuncio?id=${quadrinho.id}" class="btn-abrir-anuncio btn btn-warning text-white">Anúncio</a>
        <button class="btn btn-warning text-white">Adicionar ao Carrinho</button>
      </div>
    `;

    this.querySelector('button').onclick = async () => {
      try {
        await adicionarItemAoCarrinho(
            {
                idQuadrinho: quadrinho.id,
                preco: quadrinho.preco,
                quantidade: 1,
                nome: quadrinho.titulo,
                urlImagem: quadrinho.urlImagem
            } 
        );

        alert('Item adicionado com sucesso!');
      } catch (error){
        alertarErro(error);
      }
    }
  }
}

customElements.define('cartao-produto', CartaoProduto, { extends: "div" });