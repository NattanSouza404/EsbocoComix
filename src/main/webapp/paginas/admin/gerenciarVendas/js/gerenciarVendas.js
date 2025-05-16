import { formatarPreco, formatarDateTime } from "/js/script.js";
import { retornarPedidos, atualizarStatusPedido, atualizarStatusItemPedido } from "/js/api/apiPedido.js";

document.getElementById("main-container").style.display = 'none';

const tabelaBody = document.getElementById("orderTable");

const pedidos = await retornarPedidos();

document.getElementById("loading").style.display = 'none';
document.getElementById("main-container").style.display = 'block';

let contador = 1;
pedidos.pedidos.forEach(pedido => {
    const tr = document.createElement('tr');

    tr.innerHTML = `
        <td>${contador}</td>
        <td>Pedido ${pedido.id}</td>
        <td>${formatarDateTime(pedido.data)}</td>
        <td>${pedido.enderecoEntrega.cep}</td>
        <td>${pedido.enderecoEntrega.cidade+", "+pedido.enderecoEntrega.estado+" - "+pedido.enderecoEntrega.pais}</td>
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

    const select = tr.querySelector('select');
    select.value = pedido.status;

    tr.querySelector('button').onclick = () => {
        confirmarAtualizarStatusPedido(pedido, select.value);
    }

    select.querySelectorAll('option').forEach(option => {
        option.disabled = true; 
    });

    if (pedido.status === 'EM_PROCESSAMENTO'){
        select.querySelector('[value="REPROVADO"').disabled = false;
        select.querySelector('[value="APROVADO"').disabled = false;
        select.querySelector('[value="CANCELADO"').disabled = false;
        select.querySelector('[value="EM_TRANSPORTE"').disabled = false;
        select.querySelector('[value="ENTREGUE"').disabled = false;
    }

    if (pedido.status.includes('TROCA')){
        select.querySelectorAll('option').forEach(option => {
            option.disabled = true; 
        });

        select.querySelector(`[value="TROCA_ACEITA"`).disabled = false;
        select.querySelector(`[value="TROCA_CONCLUIDA"`).disabled = false;
        select.querySelector(`[value="TROCA_RECUSADA"`).disabled = false;
    }

    if (pedido.status.includes('DEVOLUCAO')){
        select.querySelectorAll('option').forEach(option => {
            option.disabled = true; 
        });

        select.querySelector(`[value="DEVOLUCAO_ACEITA"`).disabled = false;
        select.querySelector(`[value="DEVOLUCAO_CONCLUIDA"`).disabled = false;
        select.querySelector(`[value="DEVOLUCAO_RECUSADA"`).disabled = false;
    }

    if (pedido.status === "ENTREGUE" || pedido.status === "TROCA_CONCLUIDA" || pedido.status === "DEVOLUCAO_CONCLUIDA"){
        select.querySelectorAll('option').forEach(option => {
            option.disabled = true; 
        });
    }

    select.querySelector(`[value="${pedido.status}"`).disabled = true;

    contador++;

    tabelaBody.append(tr);
});

const tabelaPedidoTroca = document.getElementById('tabela-pedido-troca');

contador = 1;

if (Array.isArray(pedidos.itensPedido) && pedidos.itensPedido != 0){
    pedidos.itensPedido.forEach(pedidoTroca => {
        const tr = document.createElement('tr');
        
        tr.innerHTML = `
            <td>${contador}</td>
            <td>Pedido de troca #${pedidoTroca.idPedido}</td>
            <td>${pedidoTroca.nomeCliente}</td>
            <td>${pedidoTroca.quantidade}x ${pedidoTroca.nomeQuadrinho}</td>
            <td class="order-status">${pedidoTroca.status}</td>
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
    
        contador++;

        const select = tr.querySelector("select");
        select.value = pedidoTroca.status;
        select.querySelector(`[value="${pedidoTroca.status}"`).disabled = true;

        tr.querySelector('button').onclick = () => {
            confirmarAtualizarStatusItemPedido(pedidoTroca, select.value);
        };
    
        tabelaPedidoTroca.append(tr);
    });
} else {
    document.getElementById('secao-pedidos-troca').innerHTML = `
        <p class="text-center">Nenhum pedido de troca</p>   
    `;
}

function confirmarAtualizarStatusPedido(pedido, status){
    const confirmacaoUsuario = confirm(`Deseja mesmo atualizar o status desse pedido para ${status}?`);

    if (confirmacaoUsuario){
        pedido.status = status;

        /*
        if (pedido.status === 'TROCA_CONCLUIDA' || pedido.status === 'DEVOLUCAO_CONCLUIDA'){
            if (confirm('Deseja retornar os itens para o estoque?')){
                retornarPedidoAoEstoque(pedido);
            }
        }
        */

        atualizarStatusPedido(pedido);
    }
}

function confirmarAtualizarStatusItemPedido(pedidoTroca, status){
    const confirmacaoUsuario = confirm(`Deseja mesmo atualizar o status desse item para ${status}?`);

    if (confirmacaoUsuario){
        pedidoTroca.status = status;

        /*
        if (pedidoTroca.status === 'TROCA_CONCLUIDA' || pedidoTroca.status === 'DEVOLUCAO_CONCLUIDA'){
            if (confirm('Deseja retornar os itens para o estoque?')){
                retornarItemAoEstoque(pedidoTroca);
            }
        }
        */

        atualizarStatusItemPedido(pedidoTroca);
    }
}