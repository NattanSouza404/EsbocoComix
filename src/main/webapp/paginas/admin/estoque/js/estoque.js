import ModalEntradaEstoque from "./modalEntradaEstoque.js";
import { consultarTodosQuadrinhos } from "/js/api/apiQuadrinho.js";

const tbody = document.getElementById("stockTable");

const quadrinhos = await consultarTodosQuadrinhos();

const modal = new ModalEntradaEstoque();

quadrinhos.forEach(quadrinho => {
    const tr = document.createElement("tr");

    tr.innerHTML = `
        <td>1</td>
        <td class="item-name">${quadrinho.titulo}</td>
        <td class="item-quantity">${quadrinho.quantidadeEstoque}</td>
        <td class="item-cost-value">Valor custo</td>
        <td class="item-precification">???</td>
        <td class="item-price">${quadrinho.preco}</td>
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


