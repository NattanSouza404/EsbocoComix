import ModalEntradaEstoque from "./modalEntradaEstoque.js";
import { consultarTodosQuadrinhos } from "/js/api/apiQuadrinho.js";
import { formatarPreco } from "/js/script.js";

const tbody = document.getElementById("stockTable");

const quadrinhos = await consultarTodosQuadrinhos();

const modal = new ModalEntradaEstoque();

let contador = 1;
quadrinhos.forEach(quadrinho => {
    const tr = document.createElement("tr");

    let estoque = quadrinho.quantidadeEstoque;

    if (quadrinho.isForaDeEstoque){
        estoque = 'Fora de Estoque';
    }

    tr.innerHTML = `
        <td>${contador++}</td>
        <td class="item-name">${quadrinho.titulo}</td>
        <td class="item-quantity">${estoque}</td>
        <td class="item-cost-value">Valor custo</td>
        <td class="item-precification">${quadrinho.grupoPrecificacao.nome}</td>
        <td class="item-price">${formatarPreco(quadrinho.preco)}</td>
        <td>
            <button class="btn btn-warning btn-sm">Fazer entrada no estoque</button>
        </td>
    `;

    const button = tr.querySelector('button');
    button.onclick = () => {
        modal.show(quadrinho);
    };
    tbody.append(tr);
});


