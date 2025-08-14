import { alertarErro } from "../../../../js/api/alertErro.js";
import { atualizarStatusPedidoPosVenda } from "../../../../js/api/apiPedidoPosVenda.js";
import { formatarDateTime } from "/js/script.js";

export class SecaoPedidosPosVenda {
    constructor(pedidosPosVenda){
        const tabelaPedidoTroca = document.getElementById('tabela-pedido-troca');

        let contador = 1;

        if (Array.isArray(pedidosPosVenda) && pedidosPosVenda != 0){
            pedidosPosVenda.forEach(pedido => {
                const tr = document.createElement('tr');
                
                tr.innerHTML = `
                    <td>${contador}</td>
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
            
                contador++;

                const select = tr.querySelector("select");
                select.value = pedido.status;
                select.querySelector(`[value="${pedido.status}"`).disabled = true;

                tr.querySelector('button').onclick = () => {
                    this.confirmarAtualizarStatusPedidoPosVenda(pedido, select.value);
                };
            
                tabelaPedidoTroca.append(tr);
            });
        } else {
            document.getElementById('secao-pedidos-troca').innerHTML = `
                <p class="text-center">Nenhum pedido de troca</p>   
            `;
        }
    }

    async confirmarAtualizarStatusPedidoPosVenda(pedidoTroca, status){
        const confirmacaoUsuario = confirm(`Deseja mesmo atualizar o status desse item para ${status}?`);

        if (!confirmacaoUsuario){
            return;
        }

        pedidoTroca.status = status;
        pedidoTroca.retornarAoEstoque = false;

        if (pedidoTroca.status === 'TROCA_CONCLUIDA' || pedidoTroca.status === 'DEVOLUCAO_CONCLUIDA'){
            if (confirm('Deseja retornar os itens para o estoque?')){
                pedidoTroca.retornarAoEstoque = true;
            }
        }

        try {
            await atualizarStatusPedidoPosVenda(pedidoTroca);
            alert("Atualizado com sucesso!");
        } catch(error){
            alertarErro(error);
        }
        
    }
}