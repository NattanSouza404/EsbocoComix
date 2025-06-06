import { formatarPreco, formatarDateTime } from "/js/script.js";
import { atualizarStatusPedido} from "../../../../js/api/apiPedido.js";

export class SecaoPedidos {
    constructor(pedidos){
        const tabelaBody = document.getElementById("tabela-pedidos");

        let contador = 1;

        pedidos.forEach(pedido => {
            const tr = document.createElement('tr');

            tr.innerHTML = `
                <td>${contador}</td>
                <td>#${pedido.id}</td>
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
                this.confirmarAtualizarStatusPedido(pedido, select.value);
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

    }

    confirmarAtualizarStatusPedido(pedido, status){
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
}