import { retornarPedidos, atualizarStatusPedido } from "/js/api/apiPedido.js";

const tabelaBody = document.getElementById("orderTable");

const pedidos = await retornarPedidos();

let contador = 1;
pedidos.forEach(pedido => {
    const tr = document.createElement('tr');

    tr.innerHTML = `
        <td>${contador}</td>
        <td>Pedido ${pedido.id}</td>
        <td>${pedido.idCliente}</td>
        <td class="order-status">${pedido.status}</td>
        <td>
            <button class="btn btn-warning btn-sm">Alterar status para</button>
            <select>
                <option value="APROVADO">Aprovado</option>
                <option value="REPROVADO">Reprovado</option>
                <option value="CANCELADO">Cancelado</option>
                <option value="EM_TRANSPORTE">Em transporte</option>
                <option value="ENTREGUE">Entregue</option>
            </select>
            
        </td>
    `;

    const select = tr.querySelector('select');
    select.value = pedido.status;

    const button = tr.querySelector('button');

    button.onclick = () => {
        pedido.status = select.value;
        atualizarStatusPedido(pedido);
    }

    contador++;

    tabelaBody.append(tr);
});

