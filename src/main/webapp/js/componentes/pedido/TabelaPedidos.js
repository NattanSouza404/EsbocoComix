import { formatarDateTime, formatarPreco } from "../../script.js";

export const TabelaPedidos = (pedidos, confirmarAtualizarStatusPedido) => {
    const tabela = document.createElement('table');
    tabela.className = "table table-bordered table-striped";

    tabela.innerHTML = /* html */ `
        <thead class="table-dark">
            <tr>
                <th>#</th>
                <th>Pedido</th>
                <th>Data</th>
                <th>CEP</th>
                <th>Endereço</th>
                <th>Valor total</th>
                <th>Frete</th>
                <th>Cliente</th>
                <th>Status</th>
                <th>Operações</th>
            </tr>
        </thead>
        <tbody id="tabela-pedidos"></tbody>
    `;

    const tbody = tabela.querySelector('tbody');

    for (let i = 0; i < pedidos.length; i++){
        tbody.append(LinhaTabelaPedidos(pedidos[i], i+1, confirmarAtualizarStatusPedido));
    }

    return tabela;
}

function LinhaTabelaPedidos(pedido, index, confirmarAtualizarStatusPedido) {
    const tr = document.createElement('tr');

    const endereco = pedido.enderecoEntrega;

    tr.innerHTML = /* html */`
        <td>${index}</td>
        <td>#${pedido.id}</td>
        <td>${formatarDateTime(pedido.data)}</td>
        <td>${pedido.enderecoEntrega.cep}</td>
        <td>${endereco.cidade+", "+endereco.estado+" - "+endereco.pais}</td>
        <td>${formatarPreco(pedido.valorTotal)}</td>
        <td>${formatarPreco(pedido.valorFrete)}</td>
        <td>${pedido.nomeCliente}</td>
        <td class="order-status">${pedido.status}</td>
        <td>
            <button class="btn btn-warning btn-sm">Alterar status para</button>
            <select>
                <option value="EM_PROCESSAMENTO">Em processamento</option>
                <option value="REPROVADO">Reprovado</option>
                <option value="APROVADO">Aprovado</option>
                <option value="CANCELADO">Cancelado</option>
                <option value="EM_TRANSPORTE">Em transporte</option>
                <option value="ENTREGUE">Entregue</option>
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

    const select = /** @type {HTMLSelectElement} */ (tr.querySelector('select'));
    select.value = pedido.status;

    tr.querySelector('button').onclick = () => {
        confirmarAtualizarStatusPedido(pedido, select.value);
    }

    select.querySelectorAll('option').forEach(option => {
        option.disabled = true; 
    });

    const status = pedido.status;

    if (status === 'EM_PROCESSAMENTO'){
        /** @type {HTMLOptionElement} */
        (select.querySelector('[value="REPROVADO"')).disabled = false;
        
        /** @type {HTMLOptionElement} */
        (select.querySelector('[value="APROVADO"')).disabled = false;
        
        /** @type {HTMLOptionElement} */
        (select.querySelector('[value="CANCELADO"')).disabled = false;

        /** @type {HTMLOptionElement} */
        (select.querySelector('[value="EM_TRANSPORTE"')).disabled = false;
        
        /** @type {HTMLOptionElement} */
        (select.querySelector('[value="ENTREGUE"')).disabled = false;
    }

    if (status.includes('TROCA')){
        select.querySelectorAll('option').forEach(option => {
            option.disabled = true; 
        });

        /** @type {HTMLOptionElement} */
        (select.querySelector(`[value="TROCA_ACEITA"`)).disabled = false;
        
        /** @type {HTMLOptionElement} */
        (select.querySelector(`[value="TROCA_CONCLUIDA"`)).disabled = false;
        
        /** @type {HTMLOptionElement} */
        (select.querySelector(`[value="TROCA_RECUSADA"`)).disabled = false;
    }

    if (status.includes('DEVOLUCAO')){
        select.querySelectorAll('option').forEach(option => {
            option.disabled = true; 
        });

        /** @type {HTMLOptionElement} */
        (select.querySelector(`[value="DEVOLUCAO_ACEITA"`)).disabled = false;
        
        /** @type {HTMLOptionElement} */
        (select.querySelector(`[value="DEVOLUCAO_CONCLUIDA"`)).disabled = false;
        
        /** @type {HTMLOptionElement} */
        (select.querySelector(`[value="DEVOLUCAO_RECUSADA"`)).disabled = false;
    }

    if (status === "ENTREGUE" || status === "TROCA_CONCLUIDA" || status === "DEVOLUCAO_CONCLUIDA"){
        select.querySelectorAll('option').forEach(option => {
            option.disabled = true; 
        });
    }

    /** @type {HTMLOptionElement} */
    (select.querySelector(`[value="${status}"`)).disabled = true;

    return tr;
}