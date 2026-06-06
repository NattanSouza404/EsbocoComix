import { adicionarItemAoCarrinho } from "@api/carrinho.api.js";
import { retornarQuadrinho } from "@api/quadrinho.api.js";
import { AnuncioProduto } from "@componentes/quadrinho/AnuncioProduto.js";
import { getUrlParam } from "../script.js";

function getElementos() {
  return {
    containerAnuncio: document.getElementById("container-anuncio"),
  };
}

export async function initPagina() {
    try {
        const id = getUrlParam('id');

        const quadrinho = await retornarQuadrinho(id);

        getElementos().containerAnuncio.append(
            AnuncioProduto(quadrinho, adicionarItem)
        );

    } catch (error){
        getElementos().containerAnuncio.innerHTML = `
            <h3 class="text-center">Produto não encontrado!</h3>
        `;
    }
}

async function adicionarItem(quadrinho){
    try {
        const inputQuantidade = /** @type {HTMLInputElement} */
            (document.getElementsByName('quantidade')[0]);

        await adicionarItemAoCarrinho(
            {
                idQuadrinho: quadrinho.id,
                preco: quadrinho.preco,
                quantidade: inputQuantidade.value,
                nome: quadrinho.titulo,
                urlImagem: quadrinho.urlImagem
            } 
        );

        alert('Item adicionado com sucesso!');
    } catch (error){
        alert("Erro ao adicionar quadrinho: "+error);
    }
}