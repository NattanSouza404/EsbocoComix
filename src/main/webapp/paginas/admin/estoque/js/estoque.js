import { consultarEntradasEstoque } from "../../../../js/api/apiEstoque.js";
import { formatarDateTime } from "../../../../js/script.js";
import ModalEntradaEstoque from "./modalEntradaEstoque.js";
import { consultarTodosQuadrinhos } from "/js/api/apiQuadrinho.js";
import { formatarPreco } from "/js/script.js";

const tbody = document.getElementById("stockTable");

const quadrinhos = await consultarTodosQuadrinhos();
const entradasEstoque = await consultarEntradasEstoque();

const modal = new ModalEntradaEstoque();

let contador = 1;
quadrinhos.forEach(quadrinho => {
    const tr = document.createElement("tr");

    let estoque = quadrinho.quantidadeEstoque;

    if (quadrinho.quantidadeEstoque < 1){
        estoque = 'Fora de Estoque';
    }

    tr.innerHTML = `
        <td>${contador++}</td>
        <td class="item-name">${quadrinho.titulo}</td>
        <td class="item-quantity">${estoque}</td>
        <td class="item-price">${formatarPreco(quadrinho.preco)}</td>
        <td class="item-precification">${quadrinho.grupoPrecificacao.nome} ${quadrinho.grupoPrecificacao.porcentagem}%</td>
        <td>
            <button class="btn btn-warning btn-sm btn-entrada-estoque">Fazer entrada no estoque</button>
        </td>
    `;

    const button = tr.querySelector('button');
    button.onclick = () => {
        modal.show(quadrinho);
    };
    tbody.append(tr);
});

entradasEstoque.forEach(entrada => {
    document.getElementById('tabela-entrada-estoque')
        .insertAdjacentHTML('beforeend', `
           <tr>
            <td>#</td>
            <td>${entrada.nomeQuadrinho}</td>
            <td>${formatarDateTime(entrada.dataEntrada)}</td>
            <td>${entrada.quantidade}</td>
            <td>${entrada.valorCusto}</td>
            <td>${entrada.fornecedor}</td>
           </tr>
        `);
});


