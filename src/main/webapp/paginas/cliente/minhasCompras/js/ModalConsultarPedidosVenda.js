import { Modal } from "../../../../js/componentes/modal.js";
import { formatarDateTime } from "../../../../js/script.js";

export class ModalConsultarPedidosPosVenda extends Modal {
    constructor(){
        const conteudoModal = ConteudoModal();

        super('modal-consultar-pedidos-pos-venda', "Pedidos Pós Venda", conteudoModal);

        this.conteudoModal = conteudoModal;
    }

    show(pedidosPosVenda){

        if (!Array.isArray(pedidosPosVenda) || pedidosPosVenda.length <= 0){
            this.conteudoModal.innerHTML = `
                <h3 class="text-center">
                    Nenhum pedido de troca ou devolução realizado.
                </h3>
            `;

            return;
        }

        this.conteudoModal.innerHTML = "";

        pedidosPosVenda.forEach(pedido => {
            this.conteudoModal.insertAdjacentHTML("beforeend", `
                <div class="justify-content-between align-items-center p-3 mb-2 bg-light rounded border">
                    <h4>Pedido de ${pedido.tipo}</h4>
                    <p>${pedido.quantidade}x ${pedido.nomeQuadrinho}</p>
                    <p>${formatarDateTime(pedido.data)}</p>
                    <p>Status: ${pedido.status}</p>
                </div>
            `);
        });

    }
}

function ConteudoModal(){
    const div = document.createElement('div');

    div.innerHTML = `
        <h3 class="text-center">
            Nenhum pedido de troca ou devolução realizado.
        </h3>
    `;

    return div;
}