import { atualizarStatusPedido } from "/js/api/apiPedido.js";
import { Modal } from "/js/componentes/modal.js";

export default class ModalPedirTroca extends Modal {
    constructor(){
        const conteudoModal = ConteudoModalPedirTroca();

        super('modal-pedir-troca', "Pedir Troca", conteudoModal);

        this.conteudoModal = conteudoModal;

        this.conteudoModal.querySelector('button').onclick = () => {
            this.enviarFormulario()
        }
    }

    show(pedido){
        const idPedido = this.conteudoModal.querySelector('#pedido-id');
        idPedido.value = pedido.id;
        super.show();
    }
    
    enviarFormulario(){
        const pedido = {
            id: this.conteudoModal.querySelector('#pedido-id').value,
            status: "TROCA_SOLICITADA"
        };

        atualizarStatusPedido(pedido);
    }

}

function ConteudoModalPedirTroca(){
    const conteudoModal = document.createElement('form');
    conteudoModal.id = "form-pedir-troca";

    conteudoModal.innerHTML = `
        <div class="mb-3">
            <label for="pedido-id" class="form-label">Número do Pedido</label>
            <input type="text" class="form-control" id="pedido-id" readonly>
        </div>
        <div class="mb-3">
            <label for="motivoTroca" class="form-label">Motivo da Troca</label>
            <textarea class="form-control" id="motivoTroca" rows="3"
                placeholder="Descreva o motivo da troca"></textarea>
        </div>
        <button type="button" class="btn btn-primary">Enviar Solicitação</button>
    `;

    return conteudoModal;
}