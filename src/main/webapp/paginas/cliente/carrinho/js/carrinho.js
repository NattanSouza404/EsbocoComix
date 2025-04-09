import { atualizarItemCarrinho, deletarItemCarrinho } from "/js/api/apiCarrinho.js";
import { retornarCarrinho } from "/js/api/apiCarrinho.js";

const carrinho = await retornarCarrinho();

carrinho.itensCarrinho.forEach(item => {
    const linha = document.createElement('tr');

    linha.innerHTML = `
        <td class="celula-produto">
            <p class="text-center">${item.nome}</p> 
            <img class="img-carrinho" src="${item.urlImagem}">
        </td>
        <td class="product-price">R$ ${item.preco}</td>
        <td class="product-quantity">
            <input name="quantidade" value=${item.quantidade}>
        </td>
        <td class="product-total">R$ ${item.quantidade * item.preco}</td>
    `;

    const td = document.createElement('td');

    let button = document.createElement('button');
    button.textContent = 'Atualizar Quantidade';
    button.className = "btn btn-warning btn-sm";

    button.onclick = () => {
        item.quantidade = document.getElementsByName('quantidade')[0].value;
        atualizarItemCarrinho(item);
    };

    td.append(button);

    button = document.createElement('button');
    button.textContent = 'Remover';
    button.className = "btn btn-danger btn-sm";

    button.onclick = () => {
        deletarItemCarrinho(item);
    };

    td.append(button);

    linha.append(td);

    document.getElementById("tabela-produtos-carrinho")
        .append(linha);
});

let valorTotal = 0;

carrinho.itensCarrinho.forEach(item => {
    valorTotal += item.preco * item.quantidade;
});

document.getElementById('total-carrinho').textContent = `R$ ${valorTotal}`;
