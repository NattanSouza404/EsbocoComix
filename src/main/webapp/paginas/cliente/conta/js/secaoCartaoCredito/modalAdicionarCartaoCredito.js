import { Modal } from "/js/componentes/modal.js";
import { montarCartaoCreditoPorForm } from "/js/script.js";
import { FormularioCartaoCredito } from "/js/componentes/forms/formCartaoCredito.js";
import { inserirCartaoCredito } from "/js/api/apiCartaoCredito.js";

export class ModalAdicionarCartaoCredito extends Modal {

    constructor() {
        const conteudoModal = ConteudoModal();

        super('modal-adicionar-cartao-credito', "Adicionar Cartão de Crédito", conteudoModal);

        this.conteudoModal = conteudoModal;

        this.conteudoModal.querySelector('.btn-salvar').onclick = () => {
            this.enviarFormulario();
        };
    }

    enviarFormulario(){
        const cartao = montarCartaoCreditoPorForm(
            this.conteudoModal
        );

        cartao.idCliente = localStorage.getItem('idcliente');

        inserirCartaoCredito(cartao);
    }

}

function ConteudoModal() {
    const form = new FormularioCartaoCredito();
    form.id = 'adicionar-cartao-credito';

    form.insertAdjacentHTML('beforeend', `
        <button type="button" class="btn-salvar">Salvar</button>
    `);

    return form;
}
