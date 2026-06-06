import { formatarDateTime, formatarPreco } from "../../script.js";

export function TabelaEntradaEstoque(entradasEstoque){
    const tabela = document.createElement('table');
    tabela.className = 'table table-bordered table-striped';

    tabela.innerHTML = /* html */ `
        <thead class="table-dark">
            <tr>
                <th>Quadrinho</th>
                <th>Data entrada</th>
                <th>Quantidade</th>
                <th>Valor de Custo</th>
                <th>Fornecedor</th>
            </tr>
        </thead>

        <tbody></tbody>
    `;

    const tBody = tabela.querySelector('tbody');

    entradasEstoque.forEach(entrada => {
        tBody.append(LinhaTabelaEntradaEstoque(entrada));
    });

    return tabela;
}

function LinhaTabelaEntradaEstoque(entrada){
    const tr = document.createElement('tr');

    tr.innerHTML = /* html */ `
        <td>${entrada.nomeQuadrinho}</td>
        <td>${formatarDateTime(entrada.dataEntrada)}</td>
        <td>${entrada.quantidade}</td>
        <td>${formatarPreco(entrada.valorCusto)}</td>
        <td>${entrada.fornecedor}</td>
    `;

    return tr;
}