import { formatarPreco } from "../../script.js";

export function TabelaEstoque(quadrinhos, modalEntradaEstoque){
    const tabela = document.createElement('table');
    tabela.className = 'table table-bordered table-striped';

    tabela.innerHTML = /* html */ `
        <thead class="table-dark">
            <tr>
                <th>#</th>
                <th>Nome</th>
                <th>Quantidade</th>
                <th>Valor de Venda</th>
                <th>Grupo de Precificação (Margem de Lucro %)</th>
                <th>Operações</th>
            </tr>
        </thead>

        <tbody></tbody>
    `;

    const tbody = tabela.querySelector('tbody');

    for (let i = 0; i < quadrinhos.length; i++){
        tbody.append(LinhaTabelaEstoque(
            i+1,
            quadrinhos[i],
            modalEntradaEstoque
        ));
    }

    return tabela;
}

function LinhaTabelaEstoque(index, quadrinho, modalEntradaEstoque){
    const tr = document.createElement("tr");

    let estoque = quadrinho.quantidadeEstoque;

    if (quadrinho.quantidadeEstoque < 1){
        estoque = 'Fora de Estoque';
    }

    tr.innerHTML = /* html */`
        <td>${index}</td>
        <td class="item-name">${quadrinho.titulo}</td>
        <td class="item-quantity">${estoque}</td>
        <td class="item-price">${formatarPreco(quadrinho.preco)}</td>
        <td class="item-precification">
            ${quadrinho.grupoPrecificacao.nome} ${quadrinho.grupoPrecificacao.porcentagem}%
        </td>
        <td>
            <button class="btn btn-warning btn-sm btn-entrada-estoque">
                Fazer entrada no estoque
            </button>
        </td>
    `;

    const button = tr.querySelector('button');
    button.onclick = () => {
        modalEntradaEstoque.show(quadrinho);
    };

    return tr;
}