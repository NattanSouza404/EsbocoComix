import { formatarDateTime } from "/js/script.js";

export class SecaoPedidosPosVenda {
    constructor(pedidosPosVenda){
        if (Array.isArray(pedidosPosVenda) && pedidosPosVenda.length > 0){
            pedidosPosVenda.forEach(pedido => {
            document.getElementById('container-pedidos-pos-venda')
                .insertAdjacentHTML("beforeend", `
                    <div class="justify-content-between align-items-center p-3 mb-2 bg-light rounded border">
                        <h4>Pedido de ${pedido.tipo}</h4>
                        <p>${pedido.quantidade}x ${pedido.nomeQuadrinho}</p>
                        <p>${formatarDateTime(pedido.data)}</p>
                        <p>Status: ${pedido.status}</p>
                    </div>
                `);
            });
        } else {
            document.getElementById('container-pedidos-pos-venda')
                .insertAdjacentHTML("beforeend", `
                    <h3 class="text-center">Nenhum pedido de troca ou devolução realizado.</h3>
                `);
        }
    }

}