import { retornarPedidos } from "../../../../js/api/apiPedido.js";
import { retornarPedidosPosVendaByCliente } from "../../../../js/api/apiPedidoPosVenda.js";
import { atualizarStatusPedido } from "../../../../../js/api/apiPedido.js";
import { atualizarStatusPedidoPosVenda, inserirPedidoPosVenda } from "../../../../../js/api/apiPedidoPosVenda.js";

import { ModalConsultarPedidosPosVenda } from "./ModalConsultarPedidosVenda.js";
import { ContainerPedido } from "./containerPedido/ContainerPedido.js";

const idCliente = localStorage.getItem('idcliente');

const pedidos = await retornarPedidos(idCliente);
const pedidosPosVenda = await retornarPedidosPosVendaByCliente(idCliente);

const modalConsultarPedidosPosVenda = new ModalConsultarPedidosPosVenda();

pedidos.forEach(pedido => {
    const containerPedido = new ContainerPedido(pedido); 

    containerPedido.querySelector('.botao-troca').onclick = () => {
        confirmarTrocaPedido(pedido);
    };
    
    containerPedido.querySelector('.botao-devolucao').onclick = () => {
        confirmarDevolucaoPedido(pedido);
    };

    containerPedido.querySelector('.botao-consultar-pedidos-pos-venda').onclick = () => {
        const pedidosPosVendaPedido = [];
        
        pedidosPosVenda.forEach(p => {
            if (p.idPedido === pedido.id){
                pedidosPosVendaPedido.push(p);
            }
        });

        modalConsultarPedidosPosVenda.show(pedidosPosVendaPedido);
    };

    const cartoesItens = containerPedido.containerItens.cartoesItens;

    for (const [cartao, item] of cartoesItens){
        cartao.querySelector('.botao-troca-item').onclick = () => {
            confirmarTrocaItem(item);
        };

        cartao.querySelector('.botao-devolucao-item').onclick = () => {
            confirmarDevolucaoItem(item);
        };
    }

    document.getElementById('container-compras').append(
        containerPedido
    );
});

async function confirmarTrocaPedido(pedido){
    const confirmacaoUsuario = confirm("Deseja realizar a troca desse pedido?");

    if (confirmacaoUsuario){
        pedido.status = 'TROCA_SOLICITADA';
        await atualizarStatusPedido(pedido);
    }
}

async function confirmarDevolucaoPedido(pedido){
    const confirmacaoUsuario = confirm("Deseja realizar a devolução desse pedido?");

    if (confirmacaoUsuario){
        pedido.status = 'DEVOLUCAO_SOLICITADA';
        await atualizarStatusPedidoPosVenda(pedido);
    }
}

async function confirmarTrocaItem(item){
    const confirmacaoUsuario = confirm(`Deseja realizar a troca de ${item.nomeQuadrinho}?`);

    if (!confirmacaoUsuario){
        return;
    }

    try {
        const quantidade = Number(prompt("Insira quantidade desse item que você quer trocar"));

        if (isNaN(quantidade)){
            throw new Error('Insira um número válido!');
        }

        await inserirPedidoPosVenda(
            {
                idPedido: item.idPedido,
                idQuadrinho: item.idQuadrinho,
                quantidade: quantidade,
                status: "TROCA_SOLICITADA",
                tipo: "TROCA"
            }
        );
    } catch (error){
        alert(error);
    }
}

async function confirmarDevolucaoItem(item){
    const confirmacaoUsuario = confirm("Deseja realizar a devolução desse item?");

    if (!confirmacaoUsuario){
        return;
    }

    try {
        const quantidade = Number(prompt("Insira quantidade desse item que você quer trocar"));

        if (isNaN(quantidade)){
            throw new Error('Insira um número válido!');
        }

        await inserirPedidoPosVenda(
            {
                idPedido: item.idPedido,
                idQuadrinho: item.idQuadrinho,
                quantidade: quantidade,
                status: "DEVOLUCAO_SOLICITADA",
                tipo: "DEVOLUCAO"
            }
        );
    } catch (error){
        alert(error);
    }
}