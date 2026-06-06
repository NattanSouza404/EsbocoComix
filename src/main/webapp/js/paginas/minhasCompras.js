import { atualizarStatusPedido, retornarPedidos } from "@api/pedido.api.js";
import { inserirPedidoPosVenda, retornarPedidosPosVendaByCliente } from "@api/pedidoPosVenda.api.js";
import { alertarErro } from "@api/alertErro.js";
import { CartaoCompra } from "@componentes/pedido/CartaoCompra.js";
import { ModalConsultarPedidosPosVenda } from "@componentes/pedido/ModalConsultarPedidosVenda.js";
import { localStorageKeys } from "../localStorage.js";

const getElementos = () => {
    return {
        containerCompras: document.getElementById('container-compras')
    };
}

const modalConsultarPedidosPosVenda = new ModalConsultarPedidosPosVenda();

export async function initPagina() {
    try {
        const idCliente = localStorage.getItem(localStorageKeys.idCliente);

        if (!idCliente || idCliente.length === 0){
            alertarErro(new Error("Você deve ter uma conta para acessar essa página!"));
            window.location.href = "/cadastrar";
        }

        const el = getElementos();

        const pedidos = await retornarPedidos(idCliente);
        const pedidosPosVenda = await retornarPedidosPosVendaByCliente(idCliente);

        if (pedidos.length === 0){
            el.containerCompras.innerHTML = /* html */ `
                <h4 class="text-center">
                    Nenhuma compra realizada.
                </h4>
            `;

            return;
        }

        pedidos.forEach(pedido => {
            el.containerCompras.append(
                new CartaoCompra(
                    pedido,
                    confirmarTrocaPedido,
                    confirmarDevolucaoPedido,
                    confirmarTrocaItem,
                    confirmarDevolucaoItem,
                    () => {
                        consultarPedidosPosVendaPedido(pedidosPosVenda, pedido)
                    }
                )
            );
        });
        
    } catch (error){
        alertarErro(error);
    }
}

async function confirmarTrocaPedido(pedido){
    try {
        const confirmacaoUsuario = confirm("Deseja realizar a troca desse pedido?");

        if (!confirmacaoUsuario){
            return;
        }

        pedido.status = 'TROCA_SOLICITADA';

        await atualizarStatusPedido(pedido);
        alert('Atualizado com sucesso!');
    } catch (error){
        alertarErro(error);
    }
}

async function confirmarDevolucaoPedido(pedido){
    try {
        const confirmacaoUsuario = confirm("Deseja realizar a devolução desse pedido?");

        if (!confirmacaoUsuario){
            return;
        }

        pedido.status = 'DEVOLUCAO_SOLICITADA';
        
        await atualizarStatusPedido(pedido);
        alert('Atualizado com sucesso!');
    } catch (error){
        alertarErro(error);
    }
}

async function confirmarTrocaItem(item){
    try {
        const confirmacaoUsuario = confirm(`Deseja realizar a troca de "${item.nomeQuadrinho}"?`);

        if (!confirmacaoUsuario){
            return;
        }
    
        const quantidade = Number(
            prompt("Insira quantidade desse item que você quer trocar")
        );

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
    try {
        const confirmacaoUsuario = confirm(`Deseja realizar a devolução de "${item.nomeQuadrinho}"?`);

        if (!confirmacaoUsuario){
            return;
        }
    
        const quantidade = Number(
            prompt("Insira quantidade desse item que você quer trocar")
        );

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

function consultarPedidosPosVendaPedido(pedidosPosVenda, pedido){
    const pedidosPosVendaPedido = [];
    
    pedidosPosVenda.forEach(p => {
        if (p.idPedido === pedido.id){
            pedidosPosVendaPedido.push(p);
        }
    });

    modalConsultarPedidosPosVenda.show(pedidosPosVendaPedido);
}