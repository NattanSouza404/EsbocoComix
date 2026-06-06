import { formatarDateTime } from "../../script.js";

export const TabelaPedidosPosVenda = (
    pedidosPosVenda,
    confirmarAtualizarStatusPedidoPosVenda
) => {
    const tabela = document.createElement('table');
    tabela.id = "secao-pedidos-troca";
    tabela.className = "table table-bordered table-striped";

    tabela.innerHTML = /* html */ `
        <thead class="table-dark">
            <tr>
                <th>#</th>
                <th>Pedido</th>
                <th>Data</th>
                <th>Cliente</th>
                <th>Quadrinho</th>
                <th>Status</th>
                <th>Operações</th>
            </tr>
        </thead>
        <tbody id="tabela-pedido-troca"></tbody>
    `;

    const tbody = tabela.querySelector('tbody');
    for (let i = 0; i < pedidosPosVenda.length; i++){
        tbody.append(LinhaTabelaPedidosPosVenda(
            pedidosPosVenda[i],
            i+1,
            confirmarAtualizarStatusPedidoPosVenda
        ))
    }

    return tabela;
}

function LinhaTabelaPedidosPosVenda(pedido, index, confirmarAtualizarStatusPedidoPosVenda){
    const tr = document.createElement('tr');
                
    tr.innerHTML = /* html */ `
        <td>${index}</td>
        <td>#${pedido.idPedido}</td>
        <td>${formatarDateTime(pedido.data)}</td>
        <td>${pedido.nomeCliente}</td>
        <td>${pedido.quantidade}x ${pedido.nomeQuadrinho}</td>
        <td class="order-status">${pedido.status}</td>
        <td>
            <button class="btn btn-warning btn-sm">
                Alterar status para
            </button>

            <select>
                <option value="TROCA_SOLICITADA">Troca solicitada</option>
                <option value="TROCA_ACEITA">Troca aceita</option>
                <option value="TROCA_CONCLUIDA">Troca concluída</option>
                <option value="TROCA_RECUSADA">Troca recusada</option>
                <option value="DEVOLUCAO_SOLICITADA">Devolução solicitada</option>
                <option value="DEVOLUCAO_ACEITA">Devolução aceita</option>
                <option value="DEVOLUCAO_CONCLUIDA">Devolução concluída</option>
                <option value="DEVOLUCAO_RECUSADA">Devolução recusada</option>
            </select>
        </td>
    `;

    const select = tr.querySelector("select");
    select.value = pedido.status;

    /** @type {HTMLSelectElement} */
    (select.querySelector(`[value="${pedido.status}"`)).disabled = true;

    tr.querySelector('button').onclick = () => {
        confirmarAtualizarStatusPedidoPosVenda(pedido, select.value);
    };

    return tr;
}