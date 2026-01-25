import { retornarPedidos } from "@api/pedido.api.js";
import { retornarPedidosPosVendaByCliente } from "@api/pedidoPosVenda.api.js";
import { atualizarStatusPedido } from "@api/pedido.api.js";
import { inserirPedidoPosVenda } from "@api/pedidoPosVenda.api.js";

import { ModalConsultarPedidosPosVenda } from "@componentes/pedido/ModalConsultarPedidosVenda.js";
import { ContainerPedido } from "@componentes/pedido/ContainerPedido.js";
import { alertarErro } from "@api/alertErro.js";
import { localStorageKeys } from "../localStorage.js";

export async function initPagina() {
    const idCliente = localStorage.getItem(localStorageKeys.idCliente);

    let pedidos;
    let pedidosPosVenda;

    try {
        pedidos = await retornarPedidos(idCliente);
        pedidosPosVenda = await retornarPedidosPosVendaByCliente(idCliente);
    } catch (error){
        if (!idCliente || idCliente.length === 0){
            alertarErro(new Error("Você deve ter uma conta para acessar essa página!"));
            window.location.href = "/cadastrar";
        } else {
            alertarErro(error);
        }
    }

    const modalConsultarPedidosPosVenda = new ModalConsultarPedidosPosVenda();

    if (pedidos.length === 0){
        document.getElementById('container-compras')
            .innerHTML = "<h4 class=\"text-center\">Nenhuma compra realizada.</h4>"
    } else {
        pedidos.forEach(pedido => {
            const containerPedido = new ContainerPedido(pedido); 

            /** @type {HTMLButtonElement} */
            (containerPedido.querySelector('.botao-troca')).onclick = () => {
                confirmarTrocaPedido(pedido);
            };
            
            /** @type {HTMLButtonElement} */
            (containerPedido.querySelector('.botao-devolucao')).onclick = () => {
                confirmarDevolucaoPedido(pedido);
            };

            /** @type {HTMLButtonElement} */
            (containerPedido.querySelector('.botao-consultar-pedidos-pos-venda')).onclick = () => {
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
    }

}

async function confirmarTrocaPedido(pedido){
    const confirmacaoUsuario = confirm("Deseja realizar a troca desse pedido?");

    if (!confirmacaoUsuario){
        return;
    }

    pedido.status = 'TROCA_SOLICITADA';

    try {
        await atualizarStatusPedido(pedido);
        alert('Atualizado com sucesso!');
    } catch (error){
        alertarErro(error);
    }
}

async function confirmarDevolucaoPedido(pedido){
    const confirmacaoUsuario = confirm("Deseja realizar a devolução desse pedido?");

    if (!confirmacaoUsuario){
        return;
    }

    pedido.status = 'DEVOLUCAO_SOLICITADA';
        
    try {
        await atualizarStatusPedido(pedido);
        alert('Atualizado com sucesso!');
    } catch (error){
        alertarErro(error);
    }
}

async function confirmarTrocaItem(item){
    const confirmacaoUsuario = confirm(`Deseja realizar a troca de "${item.nomeQuadrinho}"?`);

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

        alert('Pedido realizado!');
    } catch (error){
        alertarErro(error);
    }
}

async function confirmarDevolucaoItem(item){
    const confirmacaoUsuario = confirm(`Deseja realizar a devolução de "${item.nomeQuadrinho}"?`);

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

        alert('Pedido realizado!');
    } catch (error){
        alertarErro(error);
    }
}