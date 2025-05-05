import { formatarPreco, formatarDateTime } from "/js/script.js";
import { retornarPedidos, atualizarStatusPedido, atualizarStatusItemPedido } from "/js/api/apiPedido.js";

const idCliente = localStorage.getItem('idcliente');

const pedidos = await retornarPedidos(idCliente);

pedidos.forEach(pedido => {
    const divPedido = document.createElement('div');

    divPedido.innerHTML = `
        <div class="p-3 mb-3 text-white rounded secao-superior">
            <div class="d-flex justify-content-between align-items-center">
                <h5 class="mb-3">Código: <span class="fw-normal">#${pedido.id}</span></h5>
                <div class="row">
                    <div class="col-sm-6">
                        <p class="mb-1"><strong>Valor total:</strong> ${formatarPreco(pedido.valorTotal)}</p>
                        <p class="mb-1"><strong>Frete:</strong> ${formatarPreco(pedido.valorFrete)}</p>
                    </div>
                    <div class="col-sm-6">
                        <p class="mb-1"><strong>Endereço:</strong> ${pedido.enderecoEntrega.id}</p>
                        <p class="mb-1"><strong>Status:</strong> ${pedido.status}</p>
                    </div>
                </div>
                <p class="mt-2"><strong>Data:</strong> ${formatarDateTime(pedido.data)}</p>
                <div>
                    <button class="btn btn-light btn-sm botao-troca">Pedir Troca</button>
                    <button class="btn btn-light btn-sm botao-devolucao">Pedir Devolução</button>
                </div>
            </div>
        </div>

        <div class="container-itens ps-4"></div>
    `;

    divPedido.querySelector('.botao-troca').onclick = () => {
        confirmarTrocaPedido(pedido);
    };

    divPedido.querySelector('.botao-devolucao').onclick = () => {
        confirmarDevolucaoPedido(pedido);
    };

    const containerItens = divPedido.querySelector('.container-itens');

    pedido.itensPedidoDTO.forEach(item => {
        containerItens.innerHTML = `
            <div class="p-3 mb-2 bg-light rounded border">
                <div class="d-flex justify-content-between align-items-center">
                    <div>
                        <h6>Produto: ${item.nomeQuadrinho}</h6>
                        <p>Quantidade: ${item.quantidade} unidades</p>
                        ${item.status !== null && item.status !== undefined ? 
                            `<p>${item.status}</p>` : ''
                        }
                    </div>
                    <div>
                        <button class="btn btn-secondary btn-sm botao-troca">Pedir Troca</button>
                        <button class="btn btn-secondary btn-sm botao-devolucao">Pedir Devolução</button>
                    </div>
                </div>
            </div>
        `;

        containerItens.querySelector('.botao-troca').onclick = () => {
            confirmarTrocaItem(item);
        };

        containerItens.querySelector('.botao-devolucao').onclick = () => {
            confirmarDevolucaoItem(item);
        };

    });

    document.getElementById('container-compras').append(divPedido);
})

function confirmarTrocaPedido(pedido){
    const confirmacaoUsuario = confirm("Deseja realizar a troca desse pedido?");

    if (confirmacaoUsuario){
        pedido.status = 'TROCA_SOLICITADA';
        atualizarStatusPedido(pedido);
    }
}

function confirmarDevolucaoPedido(pedido){
    const confirmacaoUsuario = confirm("Deseja realizar a devolução desse pedido?");

    if (confirmacaoUsuario){
        pedido.status = 'DEVOLUCAO_SOLICITADA';
        atualizarStatusPedido(pedido);
    }
}

function confirmarTrocaItem(item){
    const confirmacaoUsuario = confirm("Deseja realizar a troca desse item?");

    if (confirmacaoUsuario){
        item.status = 'TROCA_SOLICITADA';
        atualizarStatusItemPedido(item);
    }
}

function confirmarDevolucaoItem(item){
    const confirmacaoUsuario = confirm("Deseja realizar a devolução desse item?");

    if (confirmacaoUsuario){
        item.status = 'DEVOLUCAO_SOLICITADA';
        atualizarStatusItemPedido(item);
    }
}