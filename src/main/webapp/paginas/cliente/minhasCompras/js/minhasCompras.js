import { formatarPreco, formatarDateTime } from "/js/script.js";
import { retornarPedidos } from "/js/api/apiPedido.js";
import ModalPedirTroca from "./modalPedirTroca.js";

const modalPedirTroca = new ModalPedirTroca();

const idCliente = localStorage.getItem('idcliente');

const pedidos = await retornarPedidos(idCliente);

pedidos.forEach(pedido => {
    const div = document.createElement('div');

    div.innerHTML = `
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
                </div>
            </div>
        </div>
    `;

    const button = div.getElementsByClassName('botao-troca')[0];
    button.onclick = () => {
        modalPedirTroca.show(pedido)  
    };

    const containerItensPedido = document.createElement("div");
    containerItensPedido.className = "ps-4";
    div.append(containerItensPedido);

    pedido.itensPedidoDTO.forEach(item => {

        const div2 = document.createElement('div');
        div2.className = "p-3 mb-2 bg-light rounded border";

        div2.innerHTML = `
            <div class="d-flex justify-content-between align-items-center">
                <div>
                    <h6>Produto: ${item.nomeQuadrinho}</h6>
                    <p>Quantidade: ${item.quantidade} unidades</p>
                    ${item.status !== null && item.status !== undefined ? 
                        `<p>${item.status}</p>` : ''
                    }
                </div>
                <button class="btn btn-secondary btn-sm botao-troca">Pedir Troca</button>
            </div>
        `

        const button = div2.getElementsByClassName('botao-troca')[0];
        button.onclick = () => {
            modalPedirTroca.abrir(item)  
        };

        containerItensPedido.append(div2);
    });

    document.getElementById('container-compras').append(div);
})