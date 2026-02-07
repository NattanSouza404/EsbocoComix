import { atualizarStatusPedido, retornarPedidos } from "@api/pedido.api.js";
import { atualizarStatusPedidoPosVenda, retornarPedidosPosVenda } from "@api/pedidoPosVenda.api.js";
import { alertarErro } from "@api/alertErro.js";
import { TabelaPedidos } from "@componentes/pedido/TabelaPedidos.js";
import { TabelaPedidosPosVenda } from "@componentes/pedidoPosVenda/TabelaPedidosPosVenda.js";

const getElementos = () => {
    return {
        mainContainer: document.getElementById('main-container'),
        loading: document.getElementById('loading'),
        containerTabelaPedidos: document.getElementById("container-tabela-pedidos"),
        containerTabelaPedidosTroca: document.getElementById('container-tabela-pedido-troca')
    }
}

export async function initPagina() {
    try {
        const el = getElementos();

        el.mainContainer.style.display = 'none';

        const pedidos = await retornarPedidos();
        const pedidosPosVenda = await retornarPedidosPosVenda();

        el.loading.style.display = 'none';
        el.mainContainer.style.display = 'block';

        if (pedidos.length > 0){
            el.containerTabelaPedidos.append(
                TabelaPedidos(pedidos, confirmarAtualizarStatusPedido)
            );
        } else {
            el.containerTabelaPedidos.innerHTML = `
                <p class="text-center">Nenhum pedido realizado</p>
            `;
        }

        if (pedidosPosVenda.length > 0){
            el.containerTabelaPedidosTroca.append(
                TabelaPedidosPosVenda(pedidosPosVenda, confirmarAtualizarStatusPedidoPosVenda)
            );
        } else {
            el.containerTabelaPedidosTroca.innerHTML = `
                <p class="text-center">Nenhum pedido de troca/devolução realizado</p>
            `;
        }
        
    } catch (error){
        alertarErro(error);
    }
}

async function confirmarAtualizarStatusPedido(pedido, status){
    try {
        const confirmacaoUsuario = confirm(
            `Deseja mesmo atualizar o status desse pedido para ${status}?`
        );

        if (!confirmacaoUsuario){
            return;
        }

        pedido.status = status;
        pedido.retornarAoEstoque = false;

        if (['TROCA_CONCLUIDA', 'DEVOLUCAO_CONCLUIDA'].includes(pedido.status)){
            if (confirm('Deseja retornar os itens para o estoque?')){
                pedido.retornarAoEstoque = true;
            }
        }

        await atualizarStatusPedido(pedido);
        alert("Atualizado com sucesso!");
    } catch (error){
        alertarErro(error);
    }
    
}

async function confirmarAtualizarStatusPedidoPosVenda(pedidoTroca, status){
    try {
        const confirmacaoUsuario = confirm(
            `Deseja mesmo atualizar o status desse item para ${status}?`
        );

        if (!confirmacaoUsuario){
            return;
        }

        pedidoTroca.status = status;
        pedidoTroca.retornarAoEstoque = false;

        if (
            pedidoTroca.status === 'TROCA_CONCLUIDA' || 
            pedidoTroca.status === 'DEVOLUCAO_CONCLUIDA'
        ){
            if (confirm('Deseja retornar os itens para o estoque?')){
                pedidoTroca.retornarAoEstoque = true;
            }
        }

        await atualizarStatusPedidoPosVenda(pedidoTroca);
        alert("Atualizado com sucesso!");
    } catch(error){
        alertarErro(error);
    }
    
}