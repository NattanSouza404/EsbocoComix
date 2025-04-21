import { formatarPreco, formatarDateTime } from "/js/script.js";
import { retornarPedidos, atualizarStatusPedido } from "/js/api/apiPedido.js";
import { retornarPedidosTroca } from "/js/api/apiPedidoTroca.js";
import { atualizarStatusItemPedido} from "/js/api/apiPedidoTroca.js";

const tabelaBody = document.getElementById("orderTable");

const pedidos = await retornarPedidos();

let contador = 1;
pedidos.forEach(pedido => {
    const tr = document.createElement('tr');

    tr.innerHTML = `
        <td>${contador}</td>
        <td>Pedido ${pedido.id}</td>
        <td>${formatarDateTime(pedido.data)}</td>
        <td>${formatarPreco(pedido.valorTotal)}</td>
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
                <option value="DEVOLUCAO_RECUSADA">Devolução recusada</option>
                <option value="DEVOLUCAO_CONCLUIDA">Devolução concluída</option>
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

const tabelaPedidoTroca = document.getElementById('tabela-pedido-troca');

const pedidosTroca = await retornarPedidosTroca();

contador = 1;

if (Array.isArray(pedidosTroca) && pedidosTroca.length != 0){
    pedidosTroca.forEach(pedidoTroca => {
        const tr = document.createElement('tr');
        
        tr.innerHTML = `
            <td>${contador}</td>
            <td>Pedido de troca #${contador}</td>
            <td>${pedidoTroca.nomeCliente}</td>
            <td>${pedidoTroca.quantidade}x ${pedidoTroca.nomeQuadrinho}</td>
            <td class="order-status">${pedidoTroca.status}</td>
            <td>
                <button class="btn btn-warning btn-sm">Alterar status para</button>
                <select>
                    <option value="TROCA_SOLICITADA">Troca solicitada</option>
                    <option value="TROCA_ACEITA">Troca aceita</option>
                    <option value="TROCA_CONCLUIDA">Troca concluída</option>
                    <option value="TROCA_RECUSADA">Troca recusada</option>
                    <option value="DEVOLUCAO_SOLICITADA">Devolução solicitada</option>
                    <option value="DEVOLUCAO_RECUSADA">Devolução recusada</option>
                    <option value="DEVOLUCAO_CONCLUIDA">Devolução concluída</option>
                </select>
            </td>
        `;
    
        contador++;

        const select = tr.querySelector("select");
        select.value = pedidoTroca.status;

        const button = tr.querySelector('button');
        button.onclick = () => {
            pedidoTroca.status = select.value;
            atualizarStatusItemPedido(pedidoTroca);
        };
    
        tabelaPedidoTroca.append(tr);
    });
}
